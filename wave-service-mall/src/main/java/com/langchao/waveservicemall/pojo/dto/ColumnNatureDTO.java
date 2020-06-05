package com.langchao.waveservicemall.pojo.dto;


import com.langchao.waveservicemall.pojo.ColumnNature;
import com.langchao.waveservicemall.pojo.NatureValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author liyuan
 * @create 2020-03-19-18:34-周四
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ColumnNatureDTO extends ColumnNature {

    /**
     * 属性名称对应的属性值
     */
    private List<NatureValue> natureValueList;

}
