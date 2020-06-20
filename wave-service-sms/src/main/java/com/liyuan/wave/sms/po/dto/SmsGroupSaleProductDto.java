package com.liyuan.wave.sms.po.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author liyuan
 * @description
 * @date 2020-06-20 15:17
 */
@Data
public class SmsGroupSaleProductDto {

    /**
     * id
     */
    private Long id;

    /**
     * 拼团价格
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
}
