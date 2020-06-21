package com.liyuan.wave.oms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liyuan.wave.po.oms.OmsOrderInfo;
import com.liyuan.wave.po.oms.vo.OmsOrderInfoDetailVo;
import com.liyuan.wave.po.oms.vo.OmsOrderInfoSaveVo;

/**
 * @author liyuan
 * @description oms_order_info
 * @email 724837404@qq.com
 * @date 2020-06-15 16:11:12
 */
public interface OmsOrderInfoService extends IService<OmsOrderInfo> {

    /**
     * @param orderNumber
     * @return
     * @description 根据订单编号加载订单详情
     */
    OmsOrderInfoDetailVo getOrderDetailByOrderNumber(String orderNumber);

    /**
     * @param userAddressId
     * @param orderNumber
     * @return
     * @description 选择收货地址
     */
    void chooseReceiveAddress(String orderNumber, Integer userAddressId);

    /**
     * @param omsOrderInfoSaveVo
     * @return
     * @description 确认商品订单
     */
    Object create(OmsOrderInfoSaveVo omsOrderInfoSaveVo);

    /**
     * @param orderNumber
     * @return
     * @description 支付
     */
    void pay(String orderNumber);
}

