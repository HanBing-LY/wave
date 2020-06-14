package com.liyuan.wave.po.entity.oms;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
/**
 * @description 购物车表
 * 
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-11 23:17:44
 */
@Data
@TableName("oms_cart_item")
public class OmsCartItemEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
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
	private BigDecimal price;
}
