package com.langchao.waveservicemall.pojo.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductSkuDto extends ProductSku {

    private ProductSkuNature productSkuNature;

    /** 属性 */
    private List<ProductSkuNatureDTO> productSkuNatureDTOList;

    private List<ProductSkuNature> productSkuNatureList;

    /** 分类属性名称 */
    private String columnName;
    /**分类属性值名称*/
    private String valueName;
}
