package com.liyuan.wave.po.entity.oms;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @description 订单设置表
 * 
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-11 23:17:45
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("oms_order_setting")
public class OmsOrderSettingEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 秒杀订单超时关闭时间(分)
	 */
	private Integer flashOrderOvertime;
	/**
	 * 正常订单超时时间(分)
	 */
	private Integer normalOrderOvertime;
	/**
	 * 发货后自动确认收货时间（天）
	 */
	private Integer confirmOvertime;
	/**
	 * 自动完成交易时间，不能申请售后（天）
	 */
	private Integer finishOvertime;
	/**
	 * 订单完成后自动好评时间（天）
	 */
	private Integer commentOvertime;

}
