package com.liyuan.wave.po.entity.oms;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.liyuan.wave.po.entity.base.BasicPo;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @description 购物车
 * 
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-11 23:17:44
 */
@Data
@TableName("shopping_cart_info")
public class ShoppingCartInfo extends BasicPo implements Serializable {

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
	 * 用户
	 */
	private Long userId;

	/**
	 * 总数量
	 */
	private Long totalCount;

	/**
	 * 总价格
	 */
	private BigDecimal totalPrice;
}
