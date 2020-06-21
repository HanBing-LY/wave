package com.liyuan.wave.po.oms.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author liyuan
 * @description
 * @email 724837404@qq.com
 * @date 2020-06-21-23:17
 */
@Data
public class OmsShoppingCartItemVo {

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



    /**
     * 产品名称
     */
    private String productName;
    /**
     * 产品封面图
     */
    private String productIcon;
    /**
     * 产品原价（默认展示价格）
     */
    private BigDecimal productOldPrice;
    /**
     * 产品特价
     */
    private BigDecimal productSalePrice;
    /**
     * 产品详情
     */
    private String productDesc;
    /**
     * 1:上架 0:下架
     */
    private Byte type;
}
