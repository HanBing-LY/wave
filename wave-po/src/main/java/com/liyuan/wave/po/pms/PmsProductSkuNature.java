package com.liyuan.wave.po.pms;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.liyuan.wave.po.base.BasicPo;
import lombok.Data;

import java.io.Serializable;

/**
 * @description pms_product_sku_nature
 * 
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-15 16:10:20
 */
@Data
@TableName("pms_product_sku_nature")
public class PmsProductSkuNature extends BasicPo implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId(type = IdType.AUTO)
    private Long id;
	/**
	 * 产品sku id
	 */
	private Long productSkuId;
	/**
	 * 分类属性id
	 */
	private Long columnNatureId;
	/**
	 * 分类属性值id
	 */
	private Long natureValueId;


}
