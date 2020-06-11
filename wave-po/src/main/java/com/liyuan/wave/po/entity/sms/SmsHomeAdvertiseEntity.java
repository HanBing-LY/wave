package com.liyuan.wave.po.entity.sms;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @description 首页轮播广告表
 * 
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-11 23:17:47
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sms_home_advertise")
public class SmsHomeAdvertiseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 
	 */
	private String name;
	/**
	 * 轮播位置：0->PC首页轮播；1->app首页轮播
	 */
	private Integer type;
	/**
	 * 
	 */
	private String pic;
	/**
	 * 
	 */
	private Date startTime;
	/**
	 * 
	 */
	private Date endTime;
	/**
	 * 上下线状态：0->下线；1->上线
	 */
	private Integer status;
	/**
	 * 点击数
	 */
	private Integer clickCount;
	/**
	 * 下单数
	 */
	private Integer orderCount;
	/**
	 * 链接地址
	 */
	private String url;
	/**
	 * 备注
	 */
	private String note;
	/**
	 * 排序
	 */
	private Integer sort;

}
