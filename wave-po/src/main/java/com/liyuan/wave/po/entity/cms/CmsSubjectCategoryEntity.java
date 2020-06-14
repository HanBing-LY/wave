package com.liyuan.wave.po.entity.cms;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
/**
 * @description 专题分类表
 * 
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-11 23:17:46
 */
@Data
@TableName("cms_subject_category")
public class CmsSubjectCategoryEntity implements Serializable {
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
	 * 分类图标
	 */
	private String icon;
	/**
	 * 专题数量
	 */
	private Integer subjectCount;
	/**
	 * 
	 */
	private Integer showStatus;
	/**
	 * 
	 */
	private Integer sort;

}
