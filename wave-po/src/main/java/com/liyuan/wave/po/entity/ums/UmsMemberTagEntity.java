package com.liyuan.wave.po.entity.ums;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @description 用户标签表
 * 
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-11 23:17:47
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("ums_member_tag")
public class UmsMemberTagEntity implements Serializable {
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
	 * 自动打标签完成订单数量
	 */
	private Integer finishOrderCount;
	/**
	 * 自动打标签完成订单金额
	 */
	private BigDecimal finishOrderAmount;

}
