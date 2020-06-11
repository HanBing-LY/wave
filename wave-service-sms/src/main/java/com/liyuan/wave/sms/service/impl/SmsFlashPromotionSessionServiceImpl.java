package com.liyuan.wave.sms.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liyuan.wave.po.entity.sms.SmsFlashPromotionSessionEntity;
import com.liyuan.wave.sms.mapper.SmsFlashPromotionSessionDao;
import com.liyuan.wave.sms.service.SmsFlashPromotionSessionService;
import org.springframework.stereotype.Service;

/**
 * @description
 *
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-11 23:17:44
 */
@Service("smsFlashPromotionSessionService")
public class SmsFlashPromotionSessionServiceImpl extends ServiceImpl<SmsFlashPromotionSessionDao, SmsFlashPromotionSessionEntity> implements SmsFlashPromotionSessionService {
}