package com.liyuan.wave.po.entity.sms;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @description 优惠券和产品的关系表
 * 
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-11 23:17:45
 */
@Data
@TableName("sms_coupon_product_relation")
public class SmsCouponProductRelationEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 
	 */
	private Long couponId;
	/**
	 * 
	 */
	private Long productId;
	/**
	 * 商品名称
	 */
	private String productName;
	/**
	 * 商品编码
	 */
	private String productSn;

}
