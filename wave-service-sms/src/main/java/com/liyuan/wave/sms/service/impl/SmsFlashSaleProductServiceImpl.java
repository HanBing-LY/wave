package com.liyuan.wave.sms.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liyuan.wave.common.exception.ExceptionCast;
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
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

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


    @Override
    public PageInfo<SmsFlashSaleProductVo> queryByPage(Integer flashSaleProductId, Integer page, Integer size) {
        return null;
    }

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

    @Override
    public void disable(String ids) {

    }

    @Override
    public void updatePrice(Integer flashSaleProductSkuId, BigDecimal price) {

    }
}