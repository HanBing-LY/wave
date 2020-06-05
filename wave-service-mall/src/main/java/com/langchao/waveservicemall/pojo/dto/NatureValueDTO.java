package com.langchao.waveservicemall.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

/**
 * @author liyuan
 * @create 2020-03-20-12:30-周五
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class NatureValueDTO extends NatureValue {

    /**
     * 库存值
     */
    private Integer stock;
}
