package com.liyuan.wave.sms.po.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author liyuan
 * @description
 * @email 724837404@qq.com
 * @date 2020-06-17-23:24
 */
@Data
public class SmsFlashSaleProductSaveVo {

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
     * 商品货号
     */
    private String articleNumber;

}
