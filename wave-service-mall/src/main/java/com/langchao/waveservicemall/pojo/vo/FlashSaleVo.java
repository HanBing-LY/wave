package com.langchao.waveservicemall.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author liyuan
 * @create 2020-03-24-16:59-周二
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FlashSaleVo implements Serializable {
    /**
     * 秒杀分类id
     */
    private Integer flashSaleProductId;
    /** 产品skuid */
    private Integer productSkuId;
    /** 产品skuid */
    private Integer productId;
    /** 产品名称 */
    private String productName;
    /** 产品封面图 */
    private String productIcon;
    /** 秒杀价格 */
    private Double flashSalePrice;
    /** 产品库存 */
    private Integer stock;
    /** 开始时间*/
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" , timezone = "GMT+8")
    private Date startTime;
    /** 结束时间*/
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" , timezone = "GMT+8")
    private Date endTime;

}
