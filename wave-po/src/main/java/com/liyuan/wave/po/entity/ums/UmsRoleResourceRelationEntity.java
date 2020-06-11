package com.liyuan.wave.po.entity.ums;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @description 后台角色资源关系表
 * 
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-11 23:17:44
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("ums_role_resource_relation")
public class UmsRoleResourceRelationEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 角色ID
	 */
	private Long roleId;
	/**
	 * 资源ID
	 */
	private Long resourceId;

}
