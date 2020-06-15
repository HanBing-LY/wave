package com.liyuan.wave.po.pms;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.liyuan.wave.po.base.BasicPo;
import lombok.Data;

import java.io.Serializable;

/**
 * @description pms_product_column
 * 
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-15 16:10:20
 */
@Data
@TableName("pms_product_column")
public class PmsProductColumn extends BasicPo implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId(type = IdType.AUTO)
    private Long id;
	/**
	 * 分类名称
	 */
	private String columnName;
	/**
	 * 分类图片
	 */
	private String columnImage;
	/**
	 * 父id
	 */
	private Long parentId;
	/**
	 * 所有父节点id: 1-3-14
	 */
	private String parentIds;
	/**
	 * 分类等级
	 */
	private Long columnLevel;
	/**
	 * 是否显示[0-不显示，1显示]
	 */
	private Byte showStatus;
	/**
	 * 分类产品有效显示数量
	 */
	private Long productCount;
	/**
	 * 排序
	 */
	private Long sort;


}
