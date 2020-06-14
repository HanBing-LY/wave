package com.liyuan.wave.po.entity.pms;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
/**
 * @description 产品分类
 * 
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-11 23:17:45
 */
@Data
@TableName("pms_product_category")
public class PmsProductCategoryEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 上机分类的编号：0表示一级分类
	 */
	private Long parentId;
	/**
	 * 
	 */
	private String name;
	/**
	 * 分类级别：0->1级；1->2级
	 */
	private Integer level;
	/**
	 * 
	 */
	private Integer productCount;
	/**
	 * 
	 */
	private String productUnit;
	/**
	 * 是否显示在导航栏：0->不显示；1->显示
	 */
	private Integer navStatus;
	/**
	 * 显示状态：0->不显示；1->显示
	 */
	private Integer showStatus;
	/**
	 * 
	 */
	private Integer sort;
	/**
	 * 图标
	 */
	private String icon;
	/**
	 * 
	 */
	private String keywords;
	/**
	 * 描述
	 */
	private String description;

}
