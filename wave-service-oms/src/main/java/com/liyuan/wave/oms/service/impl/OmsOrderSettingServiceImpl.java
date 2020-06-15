package com.liyuan.wave.oms.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.liyuan.wave.oms.mapper.OmsOrderSettingMapper;
import com.liyuan.wave.po.oms.OmsOrderSetting;
import com.liyuan.wave.oms.service.OmsOrderSettingService;

/**
 * @description oms_order_setting
 *
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-15 16:11:12
 */
@Service("omsOrderSettingService")
public class OmsOrderSettingServiceImpl extends ServiceImpl<OmsOrderSettingMapper, OmsOrderSetting> implements OmsOrderSettingService {

}