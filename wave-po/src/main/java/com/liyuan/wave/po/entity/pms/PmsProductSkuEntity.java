package com.liyuan.wave.po.entity.pms;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.liyuan.wave.po.entity.base.BasicPo;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @description pms_product_sku
 * 
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-15 16:10:20
 */
@Data
@TableName("pms_product_sku")
public class PmsProductSkuEntity extends BasicPo implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Long id;
	/**
	 * 产品id
	 */
	private Long productId;
	/**
	 * 商品货号
	 */
	private String articleNumber;
	/**
	 * 产品价格
	 */
	private BigDecimal productPrice;
	/**
	 * 库存
	 */
	private Long stock;


}
