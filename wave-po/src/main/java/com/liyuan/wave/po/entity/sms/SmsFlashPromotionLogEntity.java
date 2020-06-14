package com.liyuan.wave.po.entity.sms;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @description 限时购通知记录
 * 
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-11 23:17:45
 */
@Data
@TableName("sms_flash_promotion_log")
public class SmsFlashPromotionLogEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 
	 */
	private Integer memberId;
	/**
	 * 
	 */
	private Long productId;
	/**
	 * 
	 */
	private String memberPhone;
	/**
	 * 
	 */
	private String productName;
	/**
	 * 会员订阅时间
	 */
	private Date subscribeTime;
	/**
	 * 
	 */
	private Date sendTime;

}
