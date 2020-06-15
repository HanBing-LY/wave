package com.liyuan.wave.po.sms;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.liyuan.wave.po.base.BasicPo;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @description sms_group_sale_product
 * 
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-15 16:09:35
 */
@Data
@TableName("sms_group_sale_product")
public class SmsGroupSaleProduct extends BasicPo implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId(type = IdType.AUTO)
    private Long id;
	/**
	 * 产品表id
	 */
	private Long productId;
	/**
	 * 拼团活动表
	 */
	private Long groupSaleId;
	/**
	 * 拼团价格
	 */
	private BigDecimal price;
	/**
	 * 产品sku id
	 */
	private Long productSkuId;
	/**
	 * 拼团人数
	 */
	private Long groupPeople;
	/**
	 * 限购数量:0 默认不限购
	 */
	private Long purchaseNumber;


}
