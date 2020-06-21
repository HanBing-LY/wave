package com.liyuan.wave.oms.po.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author liyuan
 * @description
 * @email 724837404@qq.com
 * @date 2020-06-21-23:52
 */
@Data
public class OmsShoppingCartInfoDto {

    private Long id;

    /**
     * 购物车编码
     */
    private String shoppingCartNumber;
    /**
     * 用户
     */
    private Long userId;
    /**
     * 总数量
     */
    private Long totalCount;
    /**
     * 总价格
     */
    private BigDecimal totalPrice;
}
