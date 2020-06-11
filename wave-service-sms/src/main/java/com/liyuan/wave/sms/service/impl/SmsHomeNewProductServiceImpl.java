package com.liyuan.wave.sms.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liyuan.wave.po.entity.sms.SmsHomeNewProductEntity;
import com.liyuan.wave.sms.mapper.SmsHomeNewProductDao;
import com.liyuan.wave.sms.service.SmsHomeNewProductService;
import org.springframework.stereotype.Service;

/**
 * @description
 *
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-11 23:17:47
 */
@Service("smsHomeNewProductService")
public class SmsHomeNewProductServiceImpl extends ServiceImpl<SmsHomeNewProductDao, SmsHomeNewProductEntity> implements SmsHomeNewProductService {
}