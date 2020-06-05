package com.langchao.waveservicemall.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.langchao.waveservicemall.pojo.OrderInfo;
import com.langchao.waveservicemall.pojo.vo.ScoreOrderInfoVo;

import java.util.Map;

/**
 * @author
 * @Title: OrderInfoService
 * @ProjectName
 * @Description: TODO
 * @date Mon Feb 17 16:34:44 CST 2020
 */
public interface OrderInfoService extends IService<OrderInfo> {
    /**
     * @return
     * @Author ws
     * @description 1-0-2-2-1 积分商品-确认订单 生成订单信息
     **/
    Map confirmOrderProduct(Integer userId, Integer productId);

    /**
     * @return
     * @Author ws
     * @description 1-0-2-2-1 积分商品-确认订单 根据订单编号获取订单信息
     **/
    ScoreOrderInfoVo getScoreOrderInfo(String orderNumber);

    /**
     * @return
     * @Author ws
     * @description 1-0-2-2-2 积分商品支付
     **/
    String payScoreOrder(String orderNumber, Double payMoney);

    /**
     * @param orderNumber 订单编号
     * @param payNumber   付订单编号
     * @param orderType   1:待付款 2:待发货 3:已发货 4:已退款
     * @param payMethod   支付方式1:微信 2:银联 3:积分
     * @Description 后台list
     * @Author Renjinliang
     * @date 2020/3/26 13:30
     */
    IPage findManagerList(Integer page, Integer size, String orderNumber, String payNumber, String orderType, String payMethod, String receivePhone, String userName);

    Integer updateLogisticsNum(Integer id, String logisticsNum);
}
