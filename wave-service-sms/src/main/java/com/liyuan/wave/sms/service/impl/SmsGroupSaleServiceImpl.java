package com.liyuan.wave.sms.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liyuan.wave.common.cache.constant.GroupSaleCache;
import com.liyuan.wave.common.exception.ExceptionCast;
import com.liyuan.wave.common.util.StringUtils;
import com.liyuan.wave.common.vo.response.PageInfo;
import com.liyuan.wave.po.sms.SmsGroupSale;
import com.liyuan.wave.sms.common.SmsExceptionCode;
import com.liyuan.wave.sms.constant.SelfCommonParam;
import com.liyuan.wave.sms.mapper.SmsGroupSaleMapper;
import com.liyuan.wave.sms.po.vo.SmsGroupSaleSaveVo;
import com.liyuan.wave.sms.po.vo.SmsGroupSaleVo;
import com.liyuan.wave.sms.service.SmsGroupSaleService;
import com.liyuan.wave.sms.util.CheckTimeUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @description sms_group_sale
 *
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-15 16:09:35
 */
@Service("smsGroupSaleService")
public class SmsGroupSaleServiceImpl extends ServiceImpl<SmsGroupSaleMapper, SmsGroupSale> implements SmsGroupSaleService {

    @Autowired
    private SmsGroupSaleMapper smsGroupSaleMapper;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void add(SmsGroupSaleSaveVo smsGroupSaleSaveVo) {
        Date startTime = smsGroupSaleSaveVo.getStartTime();
        Date endTime = smsGroupSaleSaveVo.getEndTime();

        if (!CheckTimeUtil.judgeStartTimeIsEffective(startTime, endTime)) {
            ExceptionCast.cast(SmsExceptionCode.TIME_NOT_TRUE);
        }

        SmsGroupSale smsGroupSale = new SmsGroupSale();
        BeanUtils.copyProperties(smsGroupSaleSaveVo, smsGroupSale);
        smsGroupSale.setId(null);

        smsGroupSaleMapper.insert(smsGroupSale);
    }

    @Override
    public void modify(SmsGroupSaleSaveVo smsGroupSaleSaveVo) {
        SmsGroupSale smsGroupSale = new SmsGroupSale();
        smsGroupSale.setId(smsGroupSaleSaveVo.getId());
        smsGroupSale.setNote(smsGroupSaleSaveVo.getNote());
        smsGroupSale.setMessage(smsGroupSaleSaveVo.getMessage());

        smsGroupSaleMapper.updateById(smsGroupSale);
    }

    @Override
    public PageInfo<SmsGroupSaleVo> queryPage(Byte status, Long pageNum, Long pageSize) {
        long start = (pageNum - 1) * pageSize;
        long end = pageNum * pageSize;
        List<String> range = stringRedisTemplate.opsForList().range(GroupSaleCache.SELF_TIME + "_" + status, start, end);
        if (range == null || range.size() == 0) {
            return new PageInfo<>();
        }

        RedisCallback<Object> redisCallback = connection -> {
            StringRedisConnection stringRedisConnection = (StringRedisConnection) connection;
            range.forEach(i -> stringRedisConnection.get(GroupSaleCache.INFO + i));
            return null;
        };
        List<Object> objects = stringRedisTemplate.executePipelined(redisCallback);
        if (StringUtils.isEmpty(objects)) {
            return new PageInfo<>();
        }

        List<SmsGroupSaleVo> collect = objects.stream().map(i -> JSONObject.parseObject(i.toString(), SmsGroupSaleVo.class)).collect(Collectors.toList());

        Long size = stringRedisTemplate.opsForList().size(GroupSaleCache.ALL_TIME);
        PageInfo<SmsGroupSaleVo> pageInfo = new PageInfo<>();
        pageInfo.setList(collect);
        pageInfo.setTotal(Optional.ofNullable(size).orElse(0L));
        return pageInfo;
    }

    @Override
    public void disable(String ids) {

        List<Long> idList = StringUtils.stringToLongList(ids);
        if (idList.size() == 0) {
            ExceptionCast.cast(SmsExceptionCode.PLEASE_CHO0SE_TO_DELETE);
        }
        SmsGroupSale smsGroupSale = new SmsGroupSale();
        smsGroupSale.setFlag(SelfCommonParam.IS_DISABLED);
        QueryWrapper<SmsGroupSale> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id", idList);
        smsGroupSaleMapper.update(smsGroupSale, queryWrapper);

    }
}