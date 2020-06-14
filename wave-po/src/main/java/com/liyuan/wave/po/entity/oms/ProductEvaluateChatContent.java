package com.liyuan.wave.po.entity.oms;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liyuan
 * @description 商品评价交谈信息
 * @email 724837404@qq.com
 * @date 2020-06-14-23:11
 */
@Data
@TableName("product_evaluate_chat_content")
@NoArgsConstructor
@AllArgsConstructor
public class ProductEvaluateChatContent {

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
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
