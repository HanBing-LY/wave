package com.liyuan.wave.po.entity.sms;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.liyuan.wave.po.entity.base.BasicPo;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @description sms_flash_sale_product
 * 
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-15 16:09:35
 */
@Data
@TableName("sms_flash_sale_product")
public class SmsFlashSaleProductEntity extends BasicPo implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Long id;
	/**
	 * 产品表id
	 */
	private Long productId;
	/**
	 * 秒杀活动表
	 */
	private Long flashSaleId;
	/**
	 * 秒杀展示价格
	 */
	private BigDecimal price;
	/**
	 * 秒杀总量
	 */
	private Long productCount;
	/**
	 * 限购数量:0 默认不限购
	 */
	private Long purchaseNumber;
	/**
	 * 产品sku id
	 */
	private Long productSkuId;


}
