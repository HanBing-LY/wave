package com.langchao.waveservicemall.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @author liyuan
 * @create 2020-03-28-9:52-周六
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GroupSaleProductSkuVo implements Serializable {

    private Integer groupSaleProductSkuId;
    /** 产品ID */
    private Integer productId;
    /** 产品SKUID */
    private Integer productSkuId;
    /** 产品名称 */
    private String productName;
    /** 几人购 */
    private Integer groupPeople;
    /** 是否超级拼购1:是 0:否 */
    private Integer superType;
    /** 属性名称 */
    private List<String> natureName;
    /** 属性值属性 */
    private List<String> natureValueName;
    /** 产品封面图 */
    private String productIcon;
    /** 拼团价格 */
    private Double groupSalePrice;
    /** 产品库存 */
    private Integer stock;

    /**
     * 产品属性id
     */
    private String natureId;
    /**
     * 产品属性值id
     */
    private String natureValueId;

    private Integer groupSaleProductId;
}
