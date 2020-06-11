package com.liyuan.wave.sms.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liyuan.wave.po.entity.sms.SmsHomeAdvertiseEntity;
import com.liyuan.wave.sms.mapper.SmsHomeAdvertiseDao;
import com.liyuan.wave.sms.service.SmsHomeAdvertiseService;
import org.springframework.stereotype.Service;

/**
 * @description
 *
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-11 23:17:47
 */
@Service("smsHomeAdvertiseService")
public class SmsHomeAdvertiseServiceImpl extends ServiceImpl<SmsHomeAdvertiseDao, SmsHomeAdvertiseEntity> implements SmsHomeAdvertiseService {
}