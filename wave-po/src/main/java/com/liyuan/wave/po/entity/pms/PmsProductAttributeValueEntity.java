package com.liyuan.wave.po.entity.pms;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @description 存储产品参数信息的表
 * 
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-11 23:17:45
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("pms_product_attribute_value")
public class PmsProductAttributeValueEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 
	 */
	private Long productId;
	/**
	 * 
	 */
	private Long productAttributeId;
	/**
	 * 手动添加规格或参数的值，参数单值，规格有多个时以逗号隔开
	 */
	private String value;

}
