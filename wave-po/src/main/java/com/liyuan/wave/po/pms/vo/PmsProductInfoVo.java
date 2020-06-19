package com.liyuan.wave.po.pms.vo;

import lombok.Data;

/**
 * @author liyuan
 * @description
 * @email 724837404@qq.com
 * @date 2020-06-17-22:55
 */
@Data
public class PmsProductInfoVo {

    /**
     * 商品id
     */
    private Long id;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品url
     */
    private String url;

    /**
     * 销量
     */
    private Long saleNumber;

    /**
     * 搜索量
     */
    private Long searchNumber;

    /**
     * 浏览量/点击量
     */
    private Long clickNumber;

    /**
     * 上架时间
     */
    private String pushTime;
}
