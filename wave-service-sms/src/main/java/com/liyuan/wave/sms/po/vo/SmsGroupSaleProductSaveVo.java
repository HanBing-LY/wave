package com.liyuan.wave.sms.po.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author liyuan
 * @description
 * @email 724837404@qq.com
 * @date 2020-06-17-23:33
 */
@Data
public class SmsGroupSaleProductSaveVo {

    /**
     * id
     */
    private Long id;

    /**
     * 拼团活动表
     */
    private Long groupSaleId;

    /**
     * 拼团展示价格
     */
    private BigDecimal price;

    /**
     * 拼团人数
     */
    private Long groupPeople;

    /**
     * 限购数量:0 默认不限购
     */
    private Long purchaseNumber;

    /**
     * 商品货号
     */
    private String articleNumber;
}
