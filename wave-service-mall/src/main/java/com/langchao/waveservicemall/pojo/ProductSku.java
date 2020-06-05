package com.langchao.waveservicemall.pojo;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.langchao.waveservicemall.pojo.base.BasicPo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author
 * @Title: ProductSku
 * @ProjectName
 * @Description: TODO
 * @date Thu Dec 26 09:52:11 CST 2019
 */
@Data
@TableName("product_sku")
@NoArgsConstructor
@AllArgsConstructor
public class ProductSku extends BasicPo implements Serializable {

    /**
     * id
     */
    @TableId
    private Integer id;
    /**
     * 产品id
     */
    private Integer productId;
    /**
     * 产品价格
     */
    private Double productPrice;
    /**
     * 库存
     */
    private Integer stock;
    /**
     * vip会员价
     */
    private Double vipPrice;
    /**
     * 俱乐部负责人价
     */
    private Double clubChargePrice;
    /**
     * 提成比例%
     */
    private Integer percentage;

}

