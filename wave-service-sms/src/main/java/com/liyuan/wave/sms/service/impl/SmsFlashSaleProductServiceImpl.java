package com.liyuan.wave.sms.service.impl;

import com.liyuan.wave.sms.po.vo.FlashSaleProductVo;
import com.liyuan.wave.sms.po.vo.SmsFlashSaleProductVo;
import com.liyuan.wave.common.vo.response.PageInfo;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.liyuan.wave.sms.mapper.SmsFlashSaleProductMapper;
import com.liyuan.wave.po.sms.SmsFlashSaleProduct;
import com.liyuan.wave.sms.service.SmsFlashSaleProductService;

import java.math.BigDecimal;

/**
 * @description sms_flash_sale_product
 *
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-15 16:09:35
 */
@Service("smsFlashSaleProductService")
public class SmsFlashSaleProductServiceImpl extends ServiceImpl<SmsFlashSaleProductMapper, SmsFlashSaleProduct> implements SmsFlashSaleProductService {

    @Override
    public PageInfo<SmsFlashSaleProductVo> queryByPage(Integer flashSaleProductId, Integer page, Integer size) {
        return null;
    }

    @Override
    public void chooseProduct(FlashSaleProductVo flashSaleProductVo) {

    }

    @Override
    public void delpl(String ids) {

    }

    @Override
    public void updatePrice(Integer flashSaleProductSkuId, BigDecimal price) {

    }
}