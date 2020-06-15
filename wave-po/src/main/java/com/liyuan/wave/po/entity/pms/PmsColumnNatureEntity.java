package com.liyuan.wave.po.entity.pms;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.liyuan.wave.po.entity.base.BasicPo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @description pms_column_nature
 * 
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-15 16:10:20
 */
@Data
@TableName("pms_column_nature")
public class PmsColumnNatureEntity extends BasicPo implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Long id;
	/**
	 * 属性名称
	 */
	private String natureName;
	/**
	 * 产品分类id
	 */
	private Long productColumnId;


}
