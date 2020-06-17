package com.liyuan.wave.pms.po.vo;

import lombok.Data;

import java.util.List;

/**
 * @author liyuan
 * @description
 * @email 724837404@qq.com
 * @date 2020-06-17-22:31
 */
@Data
public class PmsProductSkuNatureVo {

    private Long productInfoId;

    private List<PmsNatureValueVo> natureValueVos;
}
