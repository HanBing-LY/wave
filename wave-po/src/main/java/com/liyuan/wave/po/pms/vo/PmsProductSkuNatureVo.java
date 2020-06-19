package com.liyuan.wave.po.pms.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author liyuan
 * @description
 * @email 724837404@qq.com
 * @date 2020-06-17-22:31
 */
@Data
public class PmsProductSkuNatureVo {

    @NotNull(message = "请选择商品")
    private Long productInfoId;

    @NotNull(message = "请输入价格")
    private BigDecimal price;

    private List<PmsNatureValueVo> natureValueVos;
}
