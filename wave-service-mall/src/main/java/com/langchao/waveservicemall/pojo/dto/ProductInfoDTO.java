package com.langchao.waveservicemall.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.List;

/**
 * @author liyuan
 * @create 2020/3/13
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductInfoDTO extends ProductInfo {

    /**
     * 判断商品是否可拼团 1:是 0:否
     */
    private Integer groupType;
    /**
     * 判断商品是否可秒杀 1:是 0:否
     */
    private Integer flashType;

    /**
     * 拼团人数(几人购)
     */
    private Integer groupPeople;

    /**
     * 总拼团人数(已拼团人数)
     */
    private Integer totalPeople;

    /**
     * 拼团价格
     */
    private Double groupSaleProductPrice;
    /**
     * 秒杀展示价格
     */
    private Double flashSaleProductPrice;

    /**
     * 距离剩余时间多少秒(88998s)不是时分秒
     */
    private Long endTimeToSeconds;

    /**
     * 好评率
     */
    private String positiveRating;

    /**
     * 产品所有拼团组合的总拼团人数
     */
    private Integer productGroupTotalPeople;

    /**
     * 拼团相关 (快速组队俱乐部)
     */
    private List<GroupInfoDTO> groupInfoDTOS;

    /**
     * 评论集合
     */
    private List<ProductEvaluateDTO> productEvaluateDTOList;

    /** 后台：产品轮播图 */
    private String[] imgList;

    /** 后台：产品轮播图 */
    private List<String> listProductTag;

    /** 后台：分类名称 */
    private String productColumnName;

    /** 秒杀价格 */
    private Double flashSalePrice;
}
