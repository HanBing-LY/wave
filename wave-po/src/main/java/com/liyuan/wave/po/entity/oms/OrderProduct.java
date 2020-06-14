package com.liyuan.wave.po.entity.oms;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.liyuan.wave.po.entity.base.BasicPo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author liyuan
 * @description 订单详情信息
 * @email 724837404@qq.com
 * @date 2020-06-11 23:17:47
 */
@Data
@TableName("order_product")
@NoArgsConstructor
@AllArgsConstructor
public class OrderProduct extends BasicPo implements Serializable {

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
     * 产品sku id
     */
    private Long productSkuId;

    /**
     * 购买数量
     */
    private Long productNum;

    /**
     * 产品单价
     */
    private BigDecimal productPrice;

    /**
     * 产品总价
     */
    private BigDecimal totalPrice;
}

