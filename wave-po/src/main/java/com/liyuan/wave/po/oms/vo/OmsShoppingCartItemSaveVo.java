package com.liyuan.wave.po.oms.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author liyuan
 * @description
 * @email 724837404@qq.com
 * @date 2020-06-22-0:13
 */
@Data
public class OmsShoppingCartItemSaveVo {

    /**
     * 购物车编码
     */
    @NotNull
    private String shoppingCartNumber;
    /**
     * 商品编码
     */
    @NotNull
    private String articleNumber;
    /**
     * 数量
     */
    @NotNull
    private Long count;
}
