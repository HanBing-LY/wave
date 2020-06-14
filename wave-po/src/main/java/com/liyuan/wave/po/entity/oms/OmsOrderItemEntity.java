package com.liyuan.wave.po.entity.oms;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
/**
 * @description 订单中所包含的商品
 * 
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-11 23:17:46
 */
@Data
@TableName("oms_order_item")
public class OmsOrderItemEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 订单id
	 */
	private Long orderId;
	/**
	 * 订单编号
	 */
	private String orderSn;
	/**
	 * 
	 */
	private Long productId;
	/**
	 * 
	 */
	private String productPic;
	/**
	 * 
	 */
	private String productName;
	/**
	 * 
	 */
	private String productBrand;
	/**
	 * 
	 */
	private String productSn;
	/**
	 * 销售价格
	 */
	private BigDecimal productPrice;
	/**
	 * 购买数量
	 */
	private Integer productQuantity;
	/**
	 * 商品sku编号
	 */
	private Long productSkuId;
	/**
	 * 商品sku条码
	 */
	private String productSkuCode;
	/**
	 * 商品分类id
	 */
	private Long productCategoryId;
	/**
	 * 商品促销名称
	 */
	private String promotionName;
	/**
	 * 商品促销分解金额
	 */
	private BigDecimal promotionAmount;
	/**
	 * 优惠券优惠分解金额
	 */
	private BigDecimal couponAmount;
	/**
	 * 积分优惠分解金额
	 */
	private BigDecimal integrationAmount;
	/**
	 * 该商品经过优惠后的分解金额
	 */
	private BigDecimal realAmount;
	/**
	 * 
	 */
	private Integer giftIntegration;
	/**
	 * 
	 */
	private Integer giftGrowth;
	/**
	 * 商品销售属性:[{"key":"颜色","value":"颜色"},{"key":"容量","value":"4G"}]
	 */
	private String productAttr;

}
