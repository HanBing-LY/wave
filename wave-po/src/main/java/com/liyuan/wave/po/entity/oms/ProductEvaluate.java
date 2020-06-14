package com.liyuan.wave.po.entity.oms;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.liyuan.wave.po.entity.base.BasicPo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


/**
 * @author liyuan
 * @description 商品评价
 * @email 724837404@qq.com
 * @date 2020-06-11 23:17:47
 */
@Data
@TableName("product_evaluate")
@NoArgsConstructor
@AllArgsConstructor
public class ProductEvaluate extends BasicPo implements Serializable {

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

