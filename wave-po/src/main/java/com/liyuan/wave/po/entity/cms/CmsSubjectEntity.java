package com.liyuan.wave.po.entity.cms;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
/**
 * @description 专题表
 * 
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-11 23:17:46
 */
@Data
@TableName("cms_subject")
public class CmsSubjectEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 
	 */
	private Long categoryId;
	/**
	 * 
	 */
	private String title;
	/**
	 * 专题主图
	 */
	private String pic;
	/**
	 * 关联产品数量
	 */
	private Integer productCount;
	/**
	 * 
	 */
	private Integer recommendStatus;
	/**
	 * 
	 */
	private Date createTime;
	/**
	 * 
	 */
	private Integer collectCount;
	/**
	 * 
	 */
	private Integer readCount;
	/**
	 * 
	 */
	private Integer commentCount;
	/**
	 * 画册图片用逗号分割
	 */
	private String albumPics;
	/**
	 * 
	 */
	private String description;
	/**
	 * 显示状态：0->不显示；1->显示
	 */
	private Integer showStatus;
	/**
	 * 
	 */
	private String content;
	/**
	 * 转发数
	 */
	private Integer forwardCount;
	/**
	 * 专题分类名称
	 */
	private String categoryName;

}
