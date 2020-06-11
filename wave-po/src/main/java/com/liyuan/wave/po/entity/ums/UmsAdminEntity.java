package com.liyuan.wave.po.entity.ums;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @description 后台用户表
 * 
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-11 23:17:46
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("ums_admin")
public class UmsAdminEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 
	 */
	private String username;
	/**
	 * 
	 */
	private String password;
	/**
	 * 头像
	 */
	private String icon;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 昵称
	 */
	private String nickName;
	/**
	 * 备注信息
	 */
	private String note;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 最后登录时间
	 */
	private Date loginTime;
	/**
	 * 帐号启用状态：0->禁用；1->启用
	 */
	private Integer status;

}
