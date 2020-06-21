package com.liyuan.wave.po.oms;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.liyuan.wave.po.base.BasicPo;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @description oms_shopping_cart_item
 * 
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-15 16:11:12
 */
@Data
@TableName("oms_shopping_cart_item")
public class OmsShoppingCartItem extends BasicPo implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId(type = IdType.AUTO)
    private Long id;
	/**
	 * 购物车编码
	 */
	private String shoppingCartNumber;
	/**
	 * 商品编码
	 */
	private String articleNumber;
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
	 * 特殊标识  0:无;1:降价
	 */
	private Byte note;
	/**
	 * 标识
	 */
	private String message;


}
