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
 * @Title: FlashSaleProduct
 * @ProjectName
 * @Description: TODO
 * @date Thu Dec 26 09:52:10 CST 2019
 */
@Data
@TableName("flash_sale_product")
@NoArgsConstructor
@AllArgsConstructor
public class FlashSaleProduct extends BasicPo implements Serializable {

    /**
     * id
     */
    @TableId
    private Integer id;
    /**
     * 产品表id
     */
    private Integer productId;
    /**
     * 秒杀活动表
     */
    private Integer flashSaleId;
    /**
     * 秒杀展示价格
     */
    private Double price;

}

