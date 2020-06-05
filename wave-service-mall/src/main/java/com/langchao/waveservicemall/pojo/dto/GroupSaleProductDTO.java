package com.langchao.waveservicemall.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

/**
 * @author liyuan
 * @create 2020-03-19-20:16-周四
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupSaleProductDTO extends GroupSaleProduct {

    /**
     * 注:如果此类添加属性;删除此属性(防止父子构造器冲突)
     */
    private String test;
}
