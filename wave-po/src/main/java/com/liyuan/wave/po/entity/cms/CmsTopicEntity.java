package com.liyuan.wave.po.entity.cms;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
/**
 * @description 话题表
 * 
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-11 23:17:45
 */
@Data
@TableName("cms_topic")
public class CmsTopicEntity implements Serializable {
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
	private String name;
	/**
	 * 
	 */
	private Date createTime;
	/**
	 * 
	 */
	private Date startTime;
	/**
	 * 
	 */
	private Date endTime;
	/**
	 * 参与人数
	 */
	private Integer attendCount;
	/**
	 * 关注人数
	 */
	private Integer attentionCount;
	/**
	 * 
	 */
	private Integer readCount;
	/**
	 * 奖品名称
	 */
	private String awardName;
	/**
	 * 参与方式
	 */
	private String attendType;
	/**
	 * 话题内容
	 */
	private String content;

}
