package com.liyuan.wave.po.sms;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.liyuan.wave.po.base.BasicPo;
import lombok.Data;

import java.io.Serializable;

/**
 * @description sms_group_info
 * 
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-15 16:09:35
 */
@Data
@TableName("sms_group_info")
public class SmsGroupInfo extends BasicPo implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId(type = IdType.AUTO)
    private Long id;
	/**
	 * 团编码，唯一标识
	 */
	private String groupNumber;
	/**
	 * 拼团活动商品id
	 */
	private Long groupSaleProductId;
	/**
	 * 成团人数
	 */
	private Long groupPeople;
	/**
	 * 发起人
	 */
	private Long userId;
	/**
	 * 团状态 0:进行中  1:已成功  2:组团失败
	 */
	private Byte status;


}
