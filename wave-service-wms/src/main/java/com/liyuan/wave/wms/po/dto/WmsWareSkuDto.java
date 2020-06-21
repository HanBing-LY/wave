package com.liyuan.wave.wms.po.dto;

import lombok.Data;

/**
 * @author liyuan
 * @description
 * @email 724837404@qq.com
 * @date 2020-06-21-20:30
 */
@Data
public class WmsWareSkuDto {

    private Long id;

    /**
     * 商品货号
     */
    private String articleNumber;

    /**
     * 库存数
     */
    private Long stock;

    /**
     * 锁定库存
     */
    private Long stockLocked;
}
