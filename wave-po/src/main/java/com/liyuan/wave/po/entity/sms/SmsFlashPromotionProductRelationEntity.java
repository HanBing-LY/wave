package com.liyuan.wave.po.entity.sms;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @description 商品限时购与商品关系表
 * 
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-11 23:17:44
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sms_flash_promotion_product_relation")
public class SmsFlashPromotionProductRelationEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 编号
	 */
	@TableId
	private Long id;
	/**
	 * 
	 */
	private Long flashPromotionId;
	/**
	 * 编号
	 */
	private Long flashPromotionSessionId;
	/**
	 * 
	 */
	private Long productId;
	/**
	 * 限时购价格
	 */
	private BigDecimal flashPromotionPrice;
	/**
	 * 限时购数量
	 */
	private Integer flashPromotionCount;
	/**
	 * 每人限购数量
	 */
	private Integer flashPromotionLimit;
	/**
	 * 排序
	 */
	private Integer sort;

}
