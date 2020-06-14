package com.liyuan.wave.po.entity.pms;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.liyuan.wave.po.entity.base.BasicPo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author liyuan
 * @description 商品sku
 * @email 724837404@qq.com
 * @date 2020-06-11 23:17:47
 */
@Data
@TableName("product_sku")
@NoArgsConstructor
@AllArgsConstructor
public class ProductSku extends BasicPo implements Serializable {

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 产品id
     */
    private Long productId;

    /**
     * 商品货号
     */
    private String articleNumber;

    /**
     * 产品价格
     */
    private BigDecimal productPrice;

    /**
     * 库存
     */
    private Long stock;

}

