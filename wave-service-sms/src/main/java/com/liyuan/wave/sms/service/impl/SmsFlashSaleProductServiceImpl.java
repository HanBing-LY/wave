package com.liyuan.wave.sms.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liyuan.wave.common.cache.constant.FlashSaleCache;
import com.liyuan.wave.common.constant.CommonParam;
import com.liyuan.wave.common.exception.ExceptionCast;
import com.liyuan.wave.common.util.StringUtils;
import com.liyuan.wave.common.vo.response.JsonResult;
import com.liyuan.wave.common.vo.response.PageInfo;
import com.liyuan.wave.po.pms.vo.PmsProductSkuVo;
import com.liyuan.wave.po.sms.SmsFlashSaleProduct;
import com.liyuan.wave.sms.client.PmsProductSkuClient;
import com.liyuan.wave.sms.common.SmsExceptionCode;
import com.liyuan.wave.sms.mapper.SmsFlashSaleProductMapper;
import com.liyuan.wave.sms.po.vo.SmsFlashSaleProductSaveVo;
import com.liyuan.wave.sms.po.vo.SmsFlashSaleProductVo;
import com.liyuan.wave.sms.service.SmsFlashSaleProductService;
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
 * @author liyuan
 * @description sms_flash_sale_product
 * @email 724837404@qq.com
 * @date 2020-06-15 16:09:35
 */
@Service("smsFlashSaleProductService")
public class SmsFlashSaleProductServiceImpl extends ServiceImpl<SmsFlashSaleProductMapper, SmsFlashSaleProduct> implements SmsFlashSaleProductService {

    @Autowired
    private PmsProductSkuClient pmsProductSkuClient;

    @Autowired
    private SmsFlashSaleProductMapper smsFlashSaleProductMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * @param pageNum
     * @param pageSize
     * @author liyuan
     * @description 分页查询秒杀时间段对应下的秒杀商品
     */
    @Override
    public PageInfo<SmsFlashSaleProductVo> queryByPage(Long flashSaleId, Long pageNum, Long pageSize) {
        long start = (pageNum - 1) * pageSize;
        long end = pageNum * pageSize;
        List<String> range = stringRedisTemplate.opsForList().range(FlashSaleCache.INFO + flashSaleId, start, end);
        if (range == null || range.size() == 0) {
            return new PageInfo<>();
        }

        RedisCallback<Object> redisCallback = connection -> {
            StringRedisConnection stringRedisConnection = (StringRedisConnection) connection;
            range.forEach(i -> stringRedisConnection.get(FlashSaleCache.INFO_PRODUCT + i));
            return null;
        };
        List<Object> objects = stringRedisTemplate.executePipelined(redisCallback);
        if (StringUtils.isEmpty(objects)) {
            return new PageInfo<>();
        }

        List<SmsFlashSaleProductVo> collect = objects.stream().map(i -> JSONObject.parseObject(i.toString(), SmsFlashSaleProductVo.class)).collect(Collectors.toList());

        Long size = stringRedisTemplate.opsForList().size(FlashSaleCache.INFO + flashSaleId);
        PageInfo<SmsFlashSaleProductVo> pageInfo = new PageInfo<>();
        pageInfo.setList(collect);
        pageInfo.setTotal(Optional.ofNullable(size).orElse(0L));
        return pageInfo;
    }

    /**
     * @param smsFlashSaleProductSaveVo
     * @author liyuan
     * @description 选择秒杀商品
     */
    @Override
    public void chooseProduct(SmsFlashSaleProductSaveVo smsFlashSaleProductSaveVo) {
        String articleNumber = smsFlashSaleProductSaveVo.getArticleNumber();
        JsonResult jsonResult = pmsProductSkuClient.getDetailByArticleNumber(articleNumber);
        Object jsonResultData = jsonResult.getData();
        PmsProductSkuVo pmsProductSkuVo = JSONObject.parseObject(jsonResultData.toString(), PmsProductSkuVo.class);
        Long productSkuId = pmsProductSkuVo.getId();
        BigDecimal skuPrice = pmsProductSkuVo.getSkuPrice();
        BigDecimal flashSalePrice = smsFlashSaleProductSaveVo.getPrice();
        if (skuPrice.compareTo(flashSalePrice) < 1) {
            ExceptionCast.cast(SmsExceptionCode.FLASH_SALE_PRICE_ERROR);
        }
        SmsFlashSaleProduct smsFlashSaleProduct = new SmsFlashSaleProduct();
        BeanUtils.copyProperties(smsFlashSaleProductSaveVo, smsFlashSaleProduct);
        smsFlashSaleProduct.setProductSkuId(productSkuId);
        smsFlashSaleProductMapper.insert(smsFlashSaleProduct);
    }

    /**
     * @param ids 删除的商品对象id
     * @author liyuan
     * @description 停止商品秒杀
     */
    @Override
    public void disable(String ids) {

        List<Long> idList = StringUtils.stringToLongList(ids);
        if (idList.size() == 0) {
            ExceptionCast.cast(SmsExceptionCode.PLEASE_CHO0SE_TO_DELETE);
        }
        SmsFlashSaleProduct smsFlashSaleProduct = new SmsFlashSaleProduct();
        smsFlashSaleProduct.setDel(CommonParam.IS_DELETED);
        QueryWrapper<SmsFlashSaleProduct> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id", idList);
        smsFlashSaleProductMapper.update(smsFlashSaleProduct, queryWrapper);

    }

    /**
     * @param smsFlashSaleProductSaveVo
     * @author liyuan
     * @description 修改秒杀商品信息
     */
    @Override
    public void updatePrice(SmsFlashSaleProductSaveVo smsFlashSaleProductSaveVo) {
        String articleNumber = smsFlashSaleProductSaveVo.getArticleNumber();
        JsonResult jsonResult = pmsProductSkuClient.getDetailByArticleNumber(articleNumber);
        Object jsonResultData = jsonResult.getData();
        PmsProductSkuVo pmsProductSkuVo = JSONObject.parseObject(jsonResultData.toString(), PmsProductSkuVo.class);
        BigDecimal skuPrice = pmsProductSkuVo.getSkuPrice();
        BigDecimal flashSalePrice = smsFlashSaleProductSaveVo.getPrice();
        if (skuPrice.compareTo(flashSalePrice) < 1) {
            ExceptionCast.cast(SmsExceptionCode.FLASH_SALE_PRICE_ERROR);
        }
        SmsFlashSaleProduct smsFlashSaleProduct = new SmsFlashSaleProduct();
        smsFlashSaleProduct.setId(smsFlashSaleProductSaveVo.getId());
        smsFlashSaleProduct.setPrice(flashSalePrice);
        smsFlashSaleProduct.setProductCount(smsFlashSaleProductSaveVo.getProductCount());
        smsFlashSaleProduct.setPurchaseNumber(smsFlashSaleProductSaveVo.getPurchaseNumber());
        smsFlashSaleProductMapper.updateById(smsFlashSaleProduct);
    }
}