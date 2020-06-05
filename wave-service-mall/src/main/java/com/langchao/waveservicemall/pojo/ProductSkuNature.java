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
 * @Title: ProductSkuNature
 * @ProjectName
 * @Description: TODO
 * @date Thu Dec 26 09:52:11 CST 2019
 */
@Data
@TableName("product_sku_nature")
@NoArgsConstructor
@AllArgsConstructor
public class ProductSkuNature extends BasicPo implements Serializable {

    /**
     * id
     */
    @TableId
    private Integer id;
    /**
     * 产品sku id
     */
    private Integer productSkuId;
    /**
     * 分类属性id
     */
    private Integer columnNatureId;
    /**
     * 分类属性值id
     */
    private Integer natureValueId;

}

