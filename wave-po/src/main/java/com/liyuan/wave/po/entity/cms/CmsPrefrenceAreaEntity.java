package com.liyuan.wave.po.entity.cms;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @description 优选专区
 * 
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-11 23:17:47
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("cms_prefrence_area")
public class CmsPrefrenceAreaEntity implements Serializable {
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
	 * 
	 */
	private String subTitle;
	/**
	 * 展示图片
	 */
	private Varbinary pic;
	/**
	 * 
	 */
	private Integer sort;
	/**
	 * 
	 */
	private Integer showStatus;

}
