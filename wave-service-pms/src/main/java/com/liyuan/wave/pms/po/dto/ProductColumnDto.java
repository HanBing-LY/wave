package com.liyuan.wave.pms.po.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author liyuan
 * @description
 * @date 2020-06-17 14:38
 */
@Data
public class ProductColumnDto implements Serializable {

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
     * 父节点
     */
    private String parentId;

    /**
     * 所有父节点
     */
    private String parentIds;

    /**
     * 分类登记
     */
    private Long columnLevel;

    /**
     * 产品数量
     */
    private Long productCount;

    /**
     * 排序
     */
    private Long sort;
}
