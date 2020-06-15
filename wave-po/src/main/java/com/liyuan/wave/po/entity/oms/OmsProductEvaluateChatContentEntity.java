package com.liyuan.wave.po.entity.oms;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.liyuan.wave.po.entity.base.BasicPo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @description oms_product_evaluate_chat_content
 * 
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-15 16:11:12
 */
@Data
@TableName("oms_product_evaluate_chat_content")
public class OmsProductEvaluateChatContentEntity extends BasicPo implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Long id;
	/**
	 * 评价id
	 */
	private Long productEvaluateId;
	/**
	 * 内容
	 */
	private String content;
	/**
	 * 回复图片
	 */
	private String imgUrl;
	/**
	 * 是否消费者: 0:消费者,1:店家
	 */
	private Byte judgeConsumer;


}
