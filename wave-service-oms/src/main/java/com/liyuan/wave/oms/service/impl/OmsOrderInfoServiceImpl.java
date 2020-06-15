package com.liyuan.wave.oms.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.liyuan.wave.oms.dao.OmsOrderInfoDao;
import com.liyuan.wave.po.entity.oms.OmsOrderInfoEntity;
import com.liyuan.wave.oms.service.OmsOrderInfoService;

/**
 * @description oms_order_info
 *
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-15 16:11:12
 */
@Service("omsOrderInfoService")
public class OmsOrderInfoServiceImpl extends ServiceImpl<OmsOrderInfoDao, OmsOrderInfoEntity> implements OmsOrderInfoService {

}