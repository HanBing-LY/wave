package com.liyuan.wave.po.entity.pms;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.liyuan.wave.po.entity.base.BasicPo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author liyuan
 * @description 商品sku属性值
 * @email 724837404@qq.com
 * @date 2020-06-11 23:17:47
 */
@Data
@TableName("product_sku_nature")
@NoArgsConstructor
@AllArgsConstructor
public class ProductSkuNature extends BasicPo implements Serializable {

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 产品sku id
     */
    private Long productSkuId;

    /**
     * 分类属性id
     */
    private Long columnNatureId;

    /**
     * 分类属性值id
     */
    private Long natureValueId;

}

