package com.liyuan.wave.pms.po.dto;

import lombok.Data;

/**
 * @author liyuan
 * @description
 * @email 724837404@qq.com
 * @date 2020-06-17-23:48
 */
@Data
public class PmsProductColumnDto {

    /**
     * id
     */
    private Long id;

    /**
     * 分类名称
     */
    private String columnName;

    /**
     * 分类图片
     */
    private String columnImage;

    /**
     * 父id
     */
    private Long parentId;

    /**
     * 所有父节点id: 1-3-14
     */
    private String parentIds;

    /**
     * 分类等级
     */
    private Long columnLevel;

    /**
     * 是否显示[0-不显示，1显示]
     */
    private Byte showStatus;

    /**
     * 分类产品有效显示数量
     */
    private Long productCount;

    /**
     * 排序
     */
    private Long sort;
}
