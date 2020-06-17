package com.liyuan.wave.pms.po.vo;

import lombok.Data;

/**
 * @author liyuan
 * @description
 * @date 2020-06-17 14:36
 */
@Data
public class ProductColumnVo {

    private Long id;

    /**
     * 名称
     */
    private String columnName;

    /**
     * 分类图片
     */
    private String columnImage;

    /**
     * 排序
     */
    private Long sort;
}
