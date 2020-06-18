package com.liyuan.wave.pms.po.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author liyuan
 * @description
 * @email 724837404@qq.com
 * @date 2020-06-17-21:58
 */
@Data
public class PmsColumnNatureDto {

    private Long id ;

    private String name;

    private Long columnId;

}
