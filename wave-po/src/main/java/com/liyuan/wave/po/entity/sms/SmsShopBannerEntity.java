package com.liyuan.wave.po.entity.sms;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.liyuan.wave.po.entity.base.BasicPo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @description sms_shop_banner
 * 
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-15 16:09:35
 */
@Data
@TableName("sms_shop_banner")
public class SmsShopBannerEntity extends BasicPo implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Long id;
	/**
	 * 图片
	 */
	private String bannerImg;
	/**
	 * 链接
	 */
	private String bannerUrl;


}
