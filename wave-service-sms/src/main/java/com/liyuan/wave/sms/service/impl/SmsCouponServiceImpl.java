package com.liyuan.wave.sms.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liyuan.wave.po.entity.sms.SmsCouponEntity;
import com.liyuan.wave.sms.mapper.SmsCouponDao;
import com.liyuan.wave.sms.service.SmsCouponService;
import org.springframework.stereotype.Service;

/**
 * @description
 *
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-11 23:17:46
 */
@Service("smsCouponService")
public class SmsCouponServiceImpl extends ServiceImpl<SmsCouponDao, SmsCouponEntity> implements SmsCouponService {

}