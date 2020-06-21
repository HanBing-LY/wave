package com.liyuan.wave.po.wms.vo;

import lombok.Data;

/**
 * @author liyuan
 * @description
 * @email 724837404@qq.com
 * @date 2020-06-21-20:46
 */
@Data
public class WmsWareSkuVo {

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
