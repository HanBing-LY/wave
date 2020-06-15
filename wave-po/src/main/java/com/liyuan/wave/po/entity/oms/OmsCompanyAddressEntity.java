package com.liyuan.wave.po.entity.oms;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.liyuan.wave.po.entity.base.BasicPo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @description oms_company_address
 * 
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-15 16:11:12
 */
@Data
@TableName("oms_company_address")
public class OmsCompanyAddressEntity extends BasicPo implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 收发货人姓名
	 */
	private String name;
	/**
	 * 收货人电话
	 */
	private String mobilePhone;
	/**
	 * 省/直辖市
	 */
	private String province;
	/**
	 * 市
	 */
	private String city;
	/**
	 * 区
	 */
	private String area;
	/**
	 * 详细地址
	 */
	private String address;
	/**
	 * 是否默认收货地址：0->否；1->是
	 */
	private Byte receiveStatus;
	/**
	 * 默认发货地址：0->否；1->是
	 */
	private Byte sendStatus;
	/**
	 * 是否删除: 默认值0;删除:1
	 */
	private Byte del;
	/**
	 * 上次修改人
	 */
	private String modifyName;
	/**
	 * 创建人
	 */
	private String createName;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 创建时间
	 */
	private Date updateTime;

}
