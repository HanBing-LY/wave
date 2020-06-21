package com.liyuan.wave.po.wms.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author liyuan
 * @description
 * @email 724837404@qq.com
 * @date 2020-06-21-20:25
 */
@Data
public class WmsWareSkuSaveVo {

    /**
     * 商品货号
     */
    @NotNull
    private String articleNumber;

    /**
     * 库存数
     */
    @NotNull
    private Long stock;
}
