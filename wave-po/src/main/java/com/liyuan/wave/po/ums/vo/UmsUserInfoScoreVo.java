package com.liyuan.wave.po.ums.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author liyuan
 * @description
 * @email 724837404@qq.com
 * @date 2020-06-21-22:03
 */
@Data
public class UmsUserInfoScoreVo {

    @NotNull
    private Long id;

    /**
     * 用户积分
     */
    @NotNull
    private Long userScore;

    /**
     * 财务贡献
     */
    @NotNull
    private BigDecimal contributeMoney;
}
