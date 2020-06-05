package com.langchao.waveservicemall.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecommendVideoProductDto extends RecommendVideoProduct {

    /**
     * 产品名称
     */
    private String productName;
}
