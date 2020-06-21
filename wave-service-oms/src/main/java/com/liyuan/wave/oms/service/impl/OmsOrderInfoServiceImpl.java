package com.liyuan.wave.oms.service.impl;

import com.liyuan.wave.po.oms.vo.OmsOrderInfoDetailVo;
import com.liyuan.wave.po.oms.vo.OmsOrderInfoSaveVo;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.liyuan.wave.oms.mapper.OmsOrderInfoMapper;
import com.liyuan.wave.po.oms.OmsOrderInfo;
import com.liyuan.wave.oms.service.OmsOrderInfoService;

/**
 * @description oms_order_info
 *
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-15 16:11:12
 */
@Service("omsOrderInfoService")
public class OmsOrderInfoServiceImpl extends ServiceImpl<OmsOrderInfoMapper, OmsOrderInfo> implements OmsOrderInfoService {

    @Override
    public OmsOrderInfoDetailVo getOrderDetailByOrderNumber(String orderNumber) {
        return null;
    }

    @Override
    public void chooseReceiveAddress(String orderNumber, Integer userAddressId) {

    }

    @Override
    public Object create(OmsOrderInfoSaveVo omsOrderInfoSaveVo) {
        return null;
    }

    @Override
    public void pay(String orderNumber) {

    }
}