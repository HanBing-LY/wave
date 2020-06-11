package com.liyuan.wave.po.entity.sms;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @description 首页推荐品牌表
 * 
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-11 23:17:47
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sms_home_brand")
public class SmsHomeBrandEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 
	 */
	private Long brandId;
	/**
	 * 
	 */
	private String brandName;
	/**
	 * 
	 */
	private Integer recommendStatus;
	/**
	 * 
	 */
	private Integer sort;

}
