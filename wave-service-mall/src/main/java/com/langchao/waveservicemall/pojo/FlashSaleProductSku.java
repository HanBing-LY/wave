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
 * @Title: FlashSaleProductSku
 * @ProjectName
 * @Description: TODO
 * @date Thu Dec 26 09:52:10 CST 2019
 */
@Data
@TableName("flash_sale_product_sku")
@NoArgsConstructor
@AllArgsConstructor
public class FlashSaleProductSku extends BasicPo implements Serializable {

    /**
     * id
     */
    @TableId
    private Integer id;
    /**
     * 秒杀活动表
     */
    private Integer flashSaleId;
    /**
     * 产品sku id
     */
    private Integer productSkuId;
    /**
     * 产品sku销售价格
     */
    private Double price;
    /**
     * 关联表FlashSaleProductId
     */
    private Integer flashSaleProductId;

}

