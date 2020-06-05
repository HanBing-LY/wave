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
 * @Title: OrderProduct
 * @ProjectName
 * @Description: TODO
 * @date Thu Dec 26 09:52:11 CST 2019
 */
@Data
@TableName("order_product")
@NoArgsConstructor
@AllArgsConstructor
public class OrderProduct extends BasicPo implements Serializable {

    /**
     * id
     */
    @TableId
    private Integer id;
    /**
     * 订单id
     */
    private Integer orderId;
    /**
     * 产品sku id
     */
    private Integer productSkuId;
    /**
     * 产品数量
     */
    private Integer productNum;
    /**
     * 产品价格
     */
    private Double productPrice;
    /**
     * 产品id
     */
    private Integer productId;
}

