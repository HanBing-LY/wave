package com.liyuan.wave.sms.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.liyuan.wave.sms.dao.SmsGroupInfoDao;
import com.liyuan.wave.po.entity.sms.SmsGroupInfoEntity;
import com.liyuan.wave.sms.service.SmsGroupInfoService;

/**
 * @description sms_group_info
 *
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-15 16:09:35
 */
@Service("smsGroupInfoService")
public class SmsGroupInfoServiceImpl extends ServiceImpl<SmsGroupInfoDao, SmsGroupInfoEntity> implements SmsGroupInfoService {

}