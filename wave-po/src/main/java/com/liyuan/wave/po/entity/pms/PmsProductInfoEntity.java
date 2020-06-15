package com.liyuan.wave.po.entity.pms;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.liyuan.wave.po.entity.base.BasicPo;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @description pms_product_info
 * 
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-15 16:10:20
 */
@Data
@TableName("pms_product_info")
public class PmsProductInfoEntity extends BasicPo implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Long id;
	/**
	 * 产品名称
	 */
	private String productName;
	/**
	 * 产品封面图
	 */
	private String productIcon;
	/**
	 * 产品轮播图
	 */
	private String productImg;
	/**
	 * 产品介绍视频
	 */
	private String productVideo;
	/**
	 * 产品原价（默认展示价格）
	 */
	private BigDecimal productOldPrice;
	/**
	 * 产品特价
	 */
	private BigDecimal productSalePrice;
	/**
	 * 产品详情
	 */
	private String productDesc;
	/**
	 * 评论次数
	 */
	private Long commentsCount;
	/**
	 * 浏览次数
	 */
	private Long viewed;
	/**
	 * 交易次数
	 */
	private Long purchaseCount;
	/**
	 * 1:上架 0:下架
	 */
	private Byte type;
	/**
	 * 最小产品分类id
	 */
	private Long productColumnId;
	/**
	 * 销量
	 */
	private Long saleNum;
	/**
	 * 排序值 值越大越靠前
	 */
	private Long orderVal;
	/**
	 * 上架时间
	 */
	private Date pushTime;
	/**
	 * 产品标签
	 */
	private String productTag;


}
