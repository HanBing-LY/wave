package com.liyuan.wave.oms.po.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author liyuan
 * @description
 * @email 724837404@qq.com
 * @date 2020-06-21-23:52
 */
@Data
public class OmsShoppingCartItemDto {

    private Long id;

    /**
     * 商品编码
     */
    private String articleNumber;
    /**
     * 购买数量
     */
    private Integer quantity;
    /**
     * 添加到购物车的价格
     */
    private BigDecimal productPrice;
    /**
     * 此商品总价格
     */
    private BigDecimal totalPrice;
    /**
     * 特殊标识  0:无;1:降价
     */
    private Byte note;
    /**
     * 标识
     */
    private String message;
}
