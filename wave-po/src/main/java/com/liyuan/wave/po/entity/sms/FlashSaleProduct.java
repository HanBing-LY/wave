package com.liyuan.wave.po.entity.sms;


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
 * @description 秒杀商品
 * @email 724837404@qq.com
 * @date 2020-06-11 23:17:47
 */
@Data
@TableName("flash_sale_product")
@NoArgsConstructor
@AllArgsConstructor
public class FlashSaleProduct extends BasicPo implements Serializable {

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 产品表id
     */
    private Long productId;
    /**
     * 秒杀活动表
     */
    private Long flashSaleId;
    /**
     * 秒杀展示价格
     */
    private BigDecimal price;
    /**
     * 秒杀总量
     */
    private Long productCount;
    /**
     * 限购数量:0 默认不限购
     */
    private Long purchaseNumber;
    /**
     * 产品sku id
     */
    private Long productSkuId;
}

