package com.liyuan.wave.po.entity.ums;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @description 会员任务表
 * 
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-11 23:17:47
 */
@Data
@TableName("ums_member_task")
public class UmsMemberTaskEntity implements Serializable {
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
	 * 赠送成长值
	 */
	private Integer growth;
	/**
	 * 赠送积分
	 */
	private Integer intergration;
	/**
	 * 任务类型：0->新手任务；1->日常任务
	 */
	private Integer type;

}
