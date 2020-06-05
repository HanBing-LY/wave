package com.langchao.waveservicemall.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author ws
 * @description TODO
 * @date 2020-03-25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FlashSaleProductVo implements Serializable {
    /** 产品id */
    private Integer productId;
    /** 产品名称 */
    private String productName;
    /** 产品封面图 */
    private String productIcon;
    /** 秒杀价格 */
    private Double flashSalePrice;
    /** 商品原价 */
    private Double productOldPrice;
    /** 产品库存 */
    private Integer stock;
    /** 产品销量 */
    private Integer saleNum;
}
