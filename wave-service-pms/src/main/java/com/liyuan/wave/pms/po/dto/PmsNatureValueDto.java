package com.liyuan.wave.pms.po.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liyuan
 * @description
 * @date 2020-06-18 14:47
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PmsNatureValueDto {

    /**
     * id
     */
    private Long id;
    /**
     * 属性id
     */
    private Long columnNatureId;
    /**
     * 属性值
     */
    private String valueDesc;
}
