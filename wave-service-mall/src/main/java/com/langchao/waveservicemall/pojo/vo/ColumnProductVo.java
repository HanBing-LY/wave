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
public class ColumnProductVo implements Serializable {
    /** 产品id */
    private Integer productId;
    /** 产品名称 */
    private String productName;
    /** 产品封面图 */
    private String productIcon;
    /** 产品原价 */
    private Double productOldPrice;
    /** 产品售价 */
    private Double productSalePrice;
    /** 产品会员价 */
    private Double vipPrice;
    /** 产品负责人价 */
    private Double clubChargePrice;
    /** 是否可拼购 */
    private boolean judgeGroupSale;
    /** 是否限时秒杀 */
    private boolean judgeFlashSale;
}
