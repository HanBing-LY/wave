package com.liyuan.wave.po.entity.cms;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
/**
 * @description 用户举报表
 * 
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-11 23:17:47
 */
@Data
@TableName("cms_member_report")
public class CmsMemberReportEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 举报类型：0->商品评价；1->话题内容；2->用户评论
	 */
	private Integer reportType;
	/**
	 * 举报人
	 */
	private String reportMemberName;
	/**
	 * 
	 */
	private Date createTime;
	/**
	 * 
	 */
	private String reportObject;
	/**
	 * 举报状态：0->未处理；1->已处理
	 */
	private Integer reportStatus;
	/**
	 * 处理结果：0->无效；1->有效；2->恶意
	 */
	private Integer handleStatus;
	/**
	 * 
	 */
	private String note;

}
