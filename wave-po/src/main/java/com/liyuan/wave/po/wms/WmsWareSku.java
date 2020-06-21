package com.liyuan.wave.po.wms;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.liyuan.wave.po.base.BasicPo;
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
@TableName("wms_ware_sku")
public class WmsWareSku extends BasicPo implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId(type = IdType.AUTO)
    private Long id;

	/**
	 * 商品货号
	 */
	private String articleNumber;

	/**
	 * 库存数
	 */
	private Long stock;

	/**
	 * 锁定库存
	 */
	private Long stockLocked;

}
