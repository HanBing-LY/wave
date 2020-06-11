package com.liyuan.wave.po.entity.cms;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @description 帮助表
 * 
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-11 23:17:44
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("cms_help")
public class CmsHelpEntity implements Serializable {
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
	private String icon;
	/**
	 * 
	 */
	private String title;
	/**
	 * 
	 */
	private Integer showStatus;
	/**
	 * 
	 */
	private Date createTime;
	/**
	 * 
	 */
	private Integer readCount;
	/**
	 * 
	 */
	private String content;

}
