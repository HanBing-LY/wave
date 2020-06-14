package com.liyuan.wave.po.entity.wms;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @description 商品库存
 *
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-11 23:17:44
 */
@Data
@TableName("ware_sku")
public class WareSku implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Long id;
	/**
	 * sku_id
	 */
	private Long skuId;

	/**
	 * 库存数
	 */
	private Long stock;

	/**
	 * 锁定库存
	 */
	private Long stockLocked;

}
