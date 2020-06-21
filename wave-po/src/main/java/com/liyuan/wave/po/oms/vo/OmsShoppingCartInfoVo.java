package com.liyuan.wave.po.oms.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author liyuan
 * @description
 * @email 724837404@qq.com
 * @date 2020-06-21-22:43
 */
@Data
public class OmsShoppingCartInfoVo {
    /**
     * 总数量
     */
    private Long totalCount;
    /**
     * 总价格
     */
    private BigDecimal totalPrice;

    /**
     * 各系列商品详情
     */
    private List<OmsShoppingCartItemVo> omsShoppingCartItemVoList;
}
