package com.langchao.waveservicemall.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author liyuan
 * @create 2020-03-26-17:07-周四
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GroupSaleVo implements Serializable {

    /**
     * 拼团分类id
     */
    private Integer groupSaleProductId;
    /** 产品skuid */
    private Integer productSkuId;
    /** 产品skuid */
    private Integer productId;
    /** 产品名称 */
    private String productName;
    /** 产品封面图 */
    private String productIcon;
    /** 拼团价格 */
    private Double groupSalePrice;
    /** 几人购 */
    private Integer groupPeople;
    /** 是否超级拼购1:是 0:否 */
    private Integer superType;
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
