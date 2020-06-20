package com.liyuan.wave.pms.po.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author liyuan
 * @description
 * @email 724837404@qq.com
 * @date 2020-06-18-23:16
 */
@Data
public class PmsProductInfoDto {

    /**
     * 商品id
     */
    private Long id;

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
     * 销量
     */
    private Long saleNumber;

    /**
     * 排序值 值越大越靠前
     */
    private Long orderVal;
}
