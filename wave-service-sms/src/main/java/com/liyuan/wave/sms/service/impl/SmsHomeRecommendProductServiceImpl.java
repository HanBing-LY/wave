package com.liyuan.wave.sms.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liyuan.wave.po.entity.sms.SmsHomeRecommendProductEntity;
import com.liyuan.wave.sms.mapper.SmsHomeRecommendProductDao;
import com.liyuan.wave.sms.service.SmsHomeRecommendProductService;
import org.springframework.stereotype.Service;

/**
 * @description
 *
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-11 23:17:46
 */
@Service("smsHomeRecommendProductService")
public class SmsHomeRecommendProductServiceImpl extends ServiceImpl<SmsHomeRecommendProductDao, SmsHomeRecommendProductEntity> implements SmsHomeRecommendProductService {
}