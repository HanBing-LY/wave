package com.liyuan.wave.po.pms.vo;

import lombok.Data;

import java.util.List;

/**
 * @author liyuan
 * @description
 * @email 724837404@qq.com
 * @date 2020-06-17-22:26
 */
@Data
public class PmsNatureValueVo {

    private Long columnNatureId;

    private List<Long> natureValueIds;
}
