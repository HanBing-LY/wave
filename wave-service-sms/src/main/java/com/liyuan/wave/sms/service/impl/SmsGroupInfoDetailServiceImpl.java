package com.liyuan.wave.sms.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.liyuan.wave.sms.dao.SmsGroupInfoDetailDao;
import com.liyuan.wave.po.entity.sms.SmsGroupInfoDetailEntity;
import com.liyuan.wave.sms.service.SmsGroupInfoDetailService;

/**
 * @description sms_group_info_detail
 *
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-15 16:09:35
 */
@Service("smsGroupInfoDetailService")
public class SmsGroupInfoDetailServiceImpl extends ServiceImpl<SmsGroupInfoDetailDao, SmsGroupInfoDetailEntity> implements SmsGroupInfoDetailService {

}