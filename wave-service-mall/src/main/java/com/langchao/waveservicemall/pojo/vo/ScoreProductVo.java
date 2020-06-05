package com.langchao.waveservicemall.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author ws
 * @description 积分商城 商品列表vo
 * @date 2020-03-22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScoreProductVo implements Serializable {
    /**
     * 产品id
     */
    private Integer ProductId;
    /**
     * 产品名称
     */
    private String productName;
    /**
     * 产品封面图
     */
    private String productIcon;
    /**
     * 产品积分售价
     */
    private Integer productScore;
    /**
     * 额外人民币价
     */
    private Double productSalePrice;
}
