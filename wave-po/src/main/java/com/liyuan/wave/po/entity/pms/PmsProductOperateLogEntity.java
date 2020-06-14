package com.liyuan.wave.po.entity.pms;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
/**
 * @description 
 * 
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-11 23:17:47
 */
@Data
@TableName("pms_product_operate_log")
public class PmsProductOperateLogEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 
	 */
	private Long productId;
	/**
	 * 
	 */
	private BigDecimal priceOld;
	/**
	 * 
	 */
	private BigDecimal priceNew;
	/**
	 * 
	 */
	private BigDecimal salePriceOld;
	/**
	 * 
	 */
	private BigDecimal salePriceNew;
	/**
	 * 赠送的积分
	 */
	private Integer giftPointOld;
	/**
	 * 
	 */
	private Integer giftPointNew;
	/**
	 * 
	 */
	private Integer usePointLimitOld;
	/**
	 * 
	 */
	private Integer usePointLimitNew;
	/**
	 * 操作人
	 */
	private String operateMan;
	/**
	 * 
	 */
	private Date createTime;

}
