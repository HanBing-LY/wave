package com.liyuan.wave.po.entity.ums;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @description 后台用户角色表
 * 
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-11 23:17:45
 */
@Data
@TableName("ums_role")
public class UmsRoleEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 描述
	 */
	private String description;
	/**
	 * 后台用户数量
	 */
	private Integer adminCount;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 启用状态：0->禁用；1->启用
	 */
	private Integer status;
	/**
	 * 
	 */
	private Integer sort;

}
