package com.liyuan.wave.sms.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liyuan.wave.common.cache.constant.GroupSaleCache;
import com.liyuan.wave.common.constant.CommonParam;
import com.liyuan.wave.common.exception.ExceptionCast;
import com.liyuan.wave.common.util.StringUtils;
import com.liyuan.wave.common.vo.response.JsonResult;
import com.liyuan.wave.common.vo.response.PageInfo;
import com.liyuan.wave.po.pms.vo.PmsProductSkuVo;
import com.liyuan.wave.po.sms.SmsGroupSaleProduct;
import com.liyuan.wave.sms.client.PmsProductSkuClient;
import com.liyuan.wave.sms.common.SmsExceptionCode;
import com.liyuan.wave.sms.mapper.SmsGroupSaleProductMapper;
import com.liyuan.wave.sms.po.vo.SmsGroupSaleProductSaveVo;
import com.liyuan.wave.sms.po.vo.SmsGroupSaleProductVo;
import com.liyuan.wave.sms.service.SmsGroupSaleProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @description sms_group_sale_product
 *
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-15 16:09:35
 */
@Service("smsGroupSaleProductService")
public class SmsGroupSaleProductServiceImpl extends ServiceImpl<SmsGroupSaleProductMapper, SmsGroupSaleProduct> implements SmsGroupSaleProductService {

    @Autowired
    private PmsProductSkuClient pmsProductSkuClient;

    @Autowired
    private SmsGroupSaleProductMapper smsGroupSaleProductMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * @param groupSaleId
     * @param pageNum
     * @param pageSize
     * @description 分页查询拼团段秒杀商品
     */
    @Override
    public PageInfo<SmsGroupSaleProductVo> listGetByGroupSaleId(Long groupSaleId, Long pageNum, Long pageSize) {
        long start = (pageNum - 1) * pageSize;
        long end = pageNum * pageSize;
        List<String> range = stringRedisTemplate.opsForList().range(GroupSaleCache.INFO + groupSaleId, start, end);
        if (range == null || range.size() == 0) {
            return new PageInfo<>();
        }

        RedisCallback<Object> redisCallback = connection -> {
            StringRedisConnection stringRedisConnection = (StringRedisConnection) connection;
            range.forEach(i -> stringRedisConnection.get(GroupSaleCache.INFO_PRODUCT + i));
            return null;
        };
        List<Object> objects = stringRedisTemplate.executePipelined(redisCallback);
        if (StringUtils.isEmpty(objects)) {
            return new PageInfo<>();
        }

        List<SmsGroupSaleProductVo> collect = objects.stream().map(i -> JSONObject.parseObject(i.toString(), SmsGroupSaleProductVo.class)).collect(Collectors.toList());

        Long size = stringRedisTemplate.opsForList().size(GroupSaleCache.INFO + groupSaleId);
        PageInfo<SmsGroupSaleProductVo> pageInfo = new PageInfo<>();
        pageInfo.setList(collect);
        pageInfo.setTotal(Optional.ofNullable(size).orElse(0L));
        return pageInfo;
    }

    /**
     * @description 选择拼团商品
     * @param smsGroupSaleProductSaveVo
     */
    @Override
    public void chooseProduct(SmsGroupSaleProductSaveVo smsGroupSaleProductSaveVo) {
        String articleNumber = smsGroupSaleProductSaveVo.getArticleNumber();
        JsonResult jsonResult = pmsProductSkuClient.getDetailByArticleNumber(articleNumber);
        Object jsonResultData = jsonResult.getData();
        PmsProductSkuVo pmsProductSkuVo = JSONObject.parseObject(jsonResultData.toString(), PmsProductSkuVo.class);
        Long productSkuId = pmsProductSkuVo.getId();
        BigDecimal skuPrice = pmsProductSkuVo.getSkuPrice();
        BigDecimal groupSalePrice = smsGroupSaleProductSaveVo.getPrice();
        if (skuPrice.compareTo(groupSalePrice) < 1) {
            ExceptionCast.cast(SmsExceptionCode.GROUP_SALE_PRICE_ERROR);
        }
        SmsGroupSaleProduct smsGroupSaleProduct = new SmsGroupSaleProduct();
        BeanUtils.copyProperties(smsGroupSaleProductSaveVo, smsGroupSaleProduct);
        smsGroupSaleProduct.setProductSkuId(productSkuId);
        smsGroupSaleProductMapper.insert(smsGroupSaleProduct);
    }

    /**
     * @description 停止
     * @param ids
     */
    @Override
    public void disable(String ids) {
        List<Long> idList = StringUtils.stringToLongList(ids);
        if (idList.size() == 0) {
            ExceptionCast.cast(SmsExceptionCode.PLEASE_CHO0SE_TO_DELETE);
        }
        SmsGroupSaleProduct smsGroupSaleProduct = new SmsGroupSaleProduct();
        smsGroupSaleProduct.setDel(CommonParam.IS_DELETED);
        QueryWrapper<SmsGroupSaleProduct> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id", idList);
        smsGroupSaleProductMapper.update(smsGroupSaleProduct, queryWrapper);
    }

    /**
     * @description 修改
     * @param smsGroupSaleProductSaveVo
     */
    @Override
    public void updatePrice(SmsGroupSaleProductSaveVo smsGroupSaleProductSaveVo) {
        String articleNumber = smsGroupSaleProductSaveVo.getArticleNumber();
        JsonResult jsonResult = pmsProductSkuClient.getDetailByArticleNumber(articleNumber);
        Object jsonResultData = jsonResult.getData();
        PmsProductSkuVo pmsProductSkuVo = JSONObject.parseObject(jsonResultData.toString(), PmsProductSkuVo.class);
        BigDecimal skuPrice = pmsProductSkuVo.getSkuPrice();
        BigDecimal flashSalePrice = smsGroupSaleProductSaveVo.getPrice();
        if (skuPrice.compareTo(flashSalePrice) < 1) {
            ExceptionCast.cast(SmsExceptionCode.GROUP_SALE_PRICE_ERROR);
        }
        SmsGroupSaleProduct smsGroupSaleProduct = new SmsGroupSaleProduct();
        smsGroupSaleProduct.setId(smsGroupSaleProductSaveVo.getId());
        smsGroupSaleProduct.setPrice(flashSalePrice);
        smsGroupSaleProduct.setGroupPeople(smsGroupSaleProductSaveVo.getGroupPeople());
        smsGroupSaleProduct.setPurchaseNumber(smsGroupSaleProductSaveVo.getPurchaseNumber());
        smsGroupSaleProductMapper.updateById(smsGroupSaleProduct);
    }
}