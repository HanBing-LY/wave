package com.liyuan.wave.po.pms.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author liyuan
 * @description
 * @email 724837404@qq.com
 * @date 2020-06-17-23:48
 */
@Data
public class PmsProductColumnSaveVo {

    /**
     * id
     */
    private Long id;

    /**
     * 分类名称
     */
    @NotNull(message = "分类名称必填")
    private String columnName;

    /**
     * 分类图片
     */
    @NotNull(message = "分类名称必填")
    private String columnImage;

    /**
     * 父id
     */
    private Long parentId;

    /**
     * 是否显示[0-不显示，1显示]
     */
    private Byte showStatus;
}
