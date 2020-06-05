package com.langchao.waveservicemall.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author liyuan
 * @create 2020-03-27-11:24-周五
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FlashSaleProductSkuVo implements Serializable {

    private Integer flashSaleProductSkuId;
    /** 产品ID */
    private Integer productId;
    /** 产品SKUID */
    private Integer productSkuId;
    /** 产品名称 */
    private String productName;
    /** 属性名称 */
    private List<String> natureName;
    /** 属性值属性 */
    private List<String> natureValueName;
    /** 产品封面图 */
    private String productIcon;
    /** 秒杀价格 */
    private Double flashSalePrice;
    /** 产品库存 */
    private Integer stock;
    /**
     * 产品属性id
     */
    private String natureId;
    /**
     * 产品属性值id
     */
    private String natureValueId;

    private Integer flashSaleProductId;
}
