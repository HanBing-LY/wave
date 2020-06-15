package com.liyuan.wave.po.entity.sms;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.liyuan.wave.po.entity.base.BasicPo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @description sms_group_info_detail
 * 
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-15 16:09:35
 */
@Data
@TableName("sms_group_info_detail")
public class SmsGroupInfoDetailEntity extends BasicPo implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Long id;
	/**
	 * 用户id
	 */
	private Long userId;
	/**
	 * 拼团信息id
	 */
	private Long groupInfoId;
	/**
	 * 订单id
	 */
	private Long orderId;


}
