package com.liyuan.wave.po.entity.ums;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @description 后台菜单表
 * 
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-11 23:17:46
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("ums_menu")
public class UmsMenuEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 父级ID
	 */
	private Long parentId;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 菜单名称
	 */
	private String title;
	/**
	 * 菜单级数
	 */
	private Integer level;
	/**
	 * 菜单排序
	 */
	private Integer sort;
	/**
	 * 前端名称
	 */
	private String name;
	/**
	 * 前端图标
	 */
	private String icon;
	/**
	 * 前端隐藏
	 */
	private Integer hidden;

}
