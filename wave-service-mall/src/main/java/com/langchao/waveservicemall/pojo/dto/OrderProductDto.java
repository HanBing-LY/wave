package com.langchao.waveservicemall.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class OrderProductDto extends OrderProduct {
    /** 产品名称 */
    private String productName;
    /** 分类属性名称 */
    private String columnName;
    /**分类属性值名称*/
    private String valueName;
}
