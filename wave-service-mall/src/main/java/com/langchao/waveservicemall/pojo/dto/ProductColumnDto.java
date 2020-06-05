package com.langchao.waveservicemall.pojo.dto;

import com.langchao.waveservicemall.pojo.ProductColumn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Description 产品分类DTO表
 * @Author Renjinliang
 * @Date 2020/3/20 15:37
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductColumnDto extends ProductColumn {

    //二级分类
    private List<ProductColumnDto> columnList;


}
