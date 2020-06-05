package com.langchao.waveservicemall.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.List;

/**
 * @author liyuan
 * @create 2020-03-19-18:31-周四
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductSkuNatureDTO extends ProductSkuNature {

    /**
     * 属性list
     */
    private List<ColumnNatureDTO> columnNatureDTOList;

    /** 分类属性名称 */
    private String columnName;

    /** 分类属性值名称 */
    private String natureName;

}
