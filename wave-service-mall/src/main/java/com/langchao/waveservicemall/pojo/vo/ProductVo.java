package com.langchao.waveservicemall.pojo.vo;

import lombok.*;

import java.io.Serializable;

/**
 * @author liyuan
 * @create 2020-03-23-14:25-周一
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class ProductVo implements Serializable {
    /**
     * 产品id
     */
    @NonNull
    private Integer productId;
    /**
     * 产品属性id
     */
    @NonNull
    private String natureId;
    /**
     * 产品属性值id
     */
    @NonNull
    private String natureValueId;
    /**
     * 购买数量
     */
    private Integer productNum;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 产品价格
     */
    private Double productPrice;
}
