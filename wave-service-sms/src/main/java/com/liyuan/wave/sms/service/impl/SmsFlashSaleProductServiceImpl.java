package com.liyuan.wave.sms.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.liyuan.wave.sms.dao.SmsFlashSaleProductDao;
import com.liyuan.wave.po.entity.sms.SmsFlashSaleProductEntity;
import com.liyuan.wave.sms.service.SmsFlashSaleProductService;

/**
 * @description sms_flash_sale_product
 *
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-15 16:09:35
 */
@Service("smsFlashSaleProductService")
public class SmsFlashSaleProductServiceImpl extends ServiceImpl<SmsFlashSaleProductDao, SmsFlashSaleProductEntity> implements SmsFlashSaleProductService {

}