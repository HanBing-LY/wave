package com.liyuan.wave.po.oms;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.liyuan.wave.po.base.BasicPo;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @description oms_order_product
 * 
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-15 16:11:12
 */
@Data
@TableName("oms_order_product")
public class OmsOrderProduct extends BasicPo implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId(type = IdType.AUTO)
    private Long id;
	/**
	 * 订单流水号
	 */
	private String orderNumber;
	/**
	 * 产品sku id
	 */
	private Long productSkuId;
	/**
	 * 购买数量
	 */
	private Long productNum;
	/**
	 * 产品单价
	 */
	private BigDecimal productPrice;
	/**
	 * 产品总价
	 */
	private BigDecimal totalPrice;


}
