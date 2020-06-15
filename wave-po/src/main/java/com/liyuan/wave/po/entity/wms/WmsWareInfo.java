package com.liyuan.wave.po.entity.wms;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.liyuan.wave.po.entity.base.BasicPo;
import lombok.Data;

import java.io.Serializable;

/**
 * @description 仓库信息
 *
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-11 23:17:44
 */
@Data
@TableName("wms_ware_info")
public class WmsWareInfo extends BasicPo implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Long id;

	/**
	 * 仓库名
	 */
	private String name;

	/**
	 * 仓库地址
	 */
	private String address;

	/**
	 * 区域编码
	 */
	private String areaCode;

}
