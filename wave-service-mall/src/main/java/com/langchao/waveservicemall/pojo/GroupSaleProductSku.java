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
 * @Title: GroupSaleProductSku
 * @ProjectName
 * @Description: TODO
 * @date Thu Dec 26 09:52:10 CST 2019
 */
@Data
@TableName("group_sale_product_sku")
@NoArgsConstructor
@AllArgsConstructor
public class GroupSaleProductSku extends BasicPo implements Serializable {

    /**
     * id
     */
    @TableId
    private Integer id;
    /**
     * 拼团活动表
     */
    private Integer groupSaleId;
    /**
     * 产品sku id
     */
    private Integer productSkuId;
    /**
     * 产品sku销售价格
     */
    private Double price;
    /**
     * 关联表GroupSaleProductId
     */
    private Integer groupSaleProductId;
}

