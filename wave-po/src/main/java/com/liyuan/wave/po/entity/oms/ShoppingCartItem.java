package com.liyuan.wave.po.entity.oms;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.liyuan.wave.po.entity.base.BasicPo;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author liyuan
 * @description
 * @email 724837404@qq.com
 * @date 2020-06-14-23:31
 */
@Data
@TableName("shopping_cart_item")
public class ShoppingCartItem extends BasicPo implements Serializable {

    /**
     * id
     */
    @TableId
    private Long id;

    /**
     * 购物车编码
     */
    private String shoppingCartNumber;

    /**
     *  商品sku
     */
    private Long productSkuId;

    /**
     * 购买数量
     */
    private Integer quantity;

    /**
     * 添加到购物车的价格
     */
    private BigDecimal productPrice;

    /**
     * 此商品总价格
     */
    private BigDecimal totalPrice;

    /**
     * 标识
     */
    private String message;

}
