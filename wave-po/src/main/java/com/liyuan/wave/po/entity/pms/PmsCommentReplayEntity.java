package com.liyuan.wave.po.entity.pms;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @description 产品评价回复表
 * 
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-11 23:17:47
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("pms_comment_replay")
public class PmsCommentReplayEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 
	 */
	private Long commentId;
	/**
	 * 
	 */
	private String memberNickName;
	/**
	 * 
	 */
	private String memberIcon;
	/**
	 * 
	 */
	private String content;
	/**
	 * 
	 */
	private Date createTime;
	/**
	 * 评论人员类型；0->会员；1->管理员
	 */
	private Integer type;

}
