package com.langchao.waveservicemall.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author ws
 * @description 积分详细信息vo
 * @date 2020-03-22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScoreProductInfoVo implements Serializable {
    /**
     * 产品名称
     */
    private String productName;
    /**
     * 产品轮播图
     */
    private String[] productImgArray;
    /**
     * 产品积分价
     */
    private Integer productScore;
    /**
     * 产品详情
     */
    private String productDesc;
    /**
     * 额外金额
     */
    private Double productSalePrice;
}
