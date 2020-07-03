package com.liyuan.wave.oms.po.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author : liyuan  
 * @description :
 * @date : 2020-07-03 16:41  
 */
@Data
public class OmsOrderProductDto {

    private Long id;
    /**
     * 订单流水号
     */
    private String orderNumber;
    /**
     * 产品sku id
     */
    private Long productSkuId;
    /**
     * 购买数量
     */
    private Long productNum;
    /**
     * 产品单价
     */
    private BigDecimal productPrice;
    /**
     * 产品总价
     */
    private BigDecimal totalPrice;
}
