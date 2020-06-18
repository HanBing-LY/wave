package com.liyuan.wave.pms.po.vo;

import lombok.Data;

import java.util.List;

/**
 * @author liyuan
 * @description
 * @email 724837404@qq.com
 * @date 2020-06-17-22:11
 */
@Data
public class PmsColumnNatureSaveVo {


    private Long id;

    private String name;

    /**
     * 所属分类
     */
    private Long productColumnId;

    /**
     * 名称
     */
    private List<String> columnNatureNames;
}
