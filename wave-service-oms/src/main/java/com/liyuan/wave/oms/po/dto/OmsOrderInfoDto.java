package com.liyuan.wave.oms.po.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author : liyuan  
 * @description :
 * @date : 2020-07-03 16:42  
 */
@Data
public class OmsOrderInfoDto {
    /**
     * id
     */
    private Long id;
    /**
     * 订单流水号
     */
    private String orderNumber;
    /**
     * 订单金额
     */
    private BigDecimal orderMoney;
    /**
     * 运费
     */
    private BigDecimal fareMoney;
    /**
     * 订单实付金额
     */
    private BigDecimal payMoney;
    /**
     * 备注
     */
    private String note;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 收货人
     */
    private String receiveName;
    /**
     * 手机号
     */
    private String receivePhone;
    /**
     * 省
     */
    private String province;
    /**
     * 市
     */
    private String district;
    /**
     * 区
     */
    private String area;
    /**
     * 详细地址
     */
    private String address;
    /**
     * 1:待付款 2:待发货 3:已发货 4:已退款
     */
    private String orderType;
    /**
     * 支付方式1:支付宝 2:微信 3:银联 多个以逗号隔开
     */
    private String payMethod;
    /**
     * 物流单号
     */
    private String logisticsNumber;


}
