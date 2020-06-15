package com.liyuan.wave.po.oms;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.liyuan.wave.po.base.BasicPo;
import lombok.Data;

import java.io.Serializable;

/**
 * @description oms_product_evaluate
 * 
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-15 16:11:12
 */
@Data
@TableName("oms_product_evaluate")
public class OmsProductEvaluate extends BasicPo implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId(type = IdType.AUTO)
    private Long id;
	/**
	 * 订单流水号
	 */
	private String orderNumber;
	/**
	 * 用户id
	 */
	private Long userId;
	/**
	 * 评价内容
	 */
	private String evaluateContent;
	/**
	 * 评价图片
	 */
	private String imgUrl;
	/**
	 * 评价星级
	 */
	private Byte star;


}
