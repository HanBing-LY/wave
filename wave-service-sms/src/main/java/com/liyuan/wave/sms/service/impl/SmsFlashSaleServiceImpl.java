package com.liyuan.wave.sms.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liyuan.wave.common.cache.constant.FlashSaleCache;
import com.liyuan.wave.common.exception.ExceptionCast;
import com.liyuan.wave.common.util.StringUtils;
import com.liyuan.wave.common.vo.response.PageInfo;
import com.liyuan.wave.po.sms.SmsFlashSale;
import com.liyuan.wave.sms.common.SmsExceptionCode;
import com.liyuan.wave.sms.constant.SelfCommonParam;
import com.liyuan.wave.sms.mapper.SmsFlashSaleMapper;
import com.liyuan.wave.sms.po.vo.SmsFlashSaleSaveVo;
import com.liyuan.wave.sms.po.vo.SmsFlashSaleVo;
import com.liyuan.wave.sms.service.SmsFlashSaleService;
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
 * @description sms_flash_sale
 *
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-15 16:09:35
 */
@Service("smsFlashSaleService")
public class SmsFlashSaleServiceImpl extends ServiceImpl<SmsFlashSaleMapper, SmsFlashSale> implements SmsFlashSaleService {

    @Autowired
    private SmsFlashSaleMapper smsFlashSaleMapper;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * @param smsFlashSaleSaveVo
     * @author liyuan
     * @description 添加秒杀控制
     */
    @Override
    public void addFlashSale(SmsFlashSaleSaveVo smsFlashSaleSaveVo) {
        Date startTime = smsFlashSaleSaveVo.getStartTime();
        Date endTime = smsFlashSaleSaveVo.getEndTime();

        if(!CheckTimeUtil.judgeStartTimeIsEffective(startTime,endTime)){
            ExceptionCast.cast(SmsExceptionCode.TIME_NOT_TRUE);
        }

        SmsFlashSale smsFlashSale = new SmsFlashSale();
        BeanUtils.copyProperties(smsFlashSaleSaveVo,smsFlashSale);
        smsFlashSale.setId(null);

        smsFlashSaleMapper.insert(smsFlashSale);
    }

    /**
     * @param smsFlashSaleSaveVo
     * @author liyuan
     * @description 修改秒杀控制  只能改主题和描述,时间禁止调整
     */
    @Override
    public void updateFlashSale(SmsFlashSaleSaveVo smsFlashSaleSaveVo) {

        SmsFlashSale smsFlashSale = new SmsFlashSale();
        smsFlashSale.setId(smsFlashSaleSaveVo.getId());
        smsFlashSale.setNote(smsFlashSaleSaveVo.getNote());
        smsFlashSale.setMessage(smsFlashSaleSaveVo.getMessage());

        smsFlashSaleMapper.updateById(smsFlashSale);

    }

    @Override
    public PageInfo<SmsFlashSaleVo> queryPage(Byte status, Long pageNum, Long pageSize) {
        long start = (pageNum-1)*pageSize;
        long end = pageNum*pageSize;
        List<String> range = stringRedisTemplate.opsForList().range(FlashSaleCache.ALL_TIME, start, end);
        if (range == null || range.size() == 0) {
            return new PageInfo<>();
        }

        RedisCallback<Object> redisCallback = connection -> {
            StringRedisConnection stringRedisConn = (StringRedisConnection) connection;
            range.forEach(i -> stringRedisConn.get(FlashSaleCache.INFO + i));
            return null;
        };
        List<Object> objects = stringRedisTemplate.executePipelined(redisCallback);
        if (StringUtils.isEmpty(objects)) {
            return new PageInfo<>();
        }

        List<SmsFlashSaleVo> collect = objects.stream().map(i -> JSONObject.parseObject(i.toString(), SmsFlashSaleVo.class)).collect(Collectors.toList());

        Long size = stringRedisTemplate.opsForList().size(FlashSaleCache.ALL_TIME);
        PageInfo<SmsFlashSaleVo> pageInfo = new PageInfo<>();
        pageInfo.setList(collect);
        pageInfo.setTotal(Optional.ofNullable(size).orElse(0L));
        return pageInfo;
    }

    @Override
    public void disable(String ids) {

        List<Long> idList = StringUtils.stringToLongList(ids);
        if(idList.size() == 0){
            ExceptionCast.cast(SmsExceptionCode.PLEASE_CHO0SE_TO_DELETE);
        }
        SmsFlashSale smsFlashSale = new SmsFlashSale();
        smsFlashSale.setFlag(SelfCommonParam.IS_DISABLED);
        QueryWrapper<SmsFlashSale> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id",idList);
        smsFlashSaleMapper.update(smsFlashSale,queryWrapper);

    }
}