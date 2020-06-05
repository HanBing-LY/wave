package com.langchao.waveservicemall.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.langchao.waveservicemall.pojo.base.BasicPo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author
 * @Title: ProductInfo
 * @ProjectName
 * @Description: TODO
 * @date Thu Dec 26 09:52:11 CST 2019
 */
@Data
@TableName("product_info")
@NoArgsConstructor
@AllArgsConstructor
public class ProductInfo extends BasicPo implements Serializable {

    /**
     * id
     */
    @TableId
    private Integer id;
    /**
     * 产品名称
     */
    private String productName;
    /**
     * 产品封面图
     */
    private String productIcon;
    /**
     * 产品轮播图
     */
    private String productImg;
    /**
     * 产品原价（展示价格）
     */
    private Double productOldPrice;
    /**
     * 产品售价（展示价格）
     */
    private Double productSalePrice;
    /**
     * 产品积分售价
     */
    private Integer productScore;
    /**
     * 产品详情
     */
    private String productDesc;
    /**
     * 1:上架 0:下架
     */
    private Integer type;
    /**
     * 产品分类id
     */
    private Integer productColumId;

    /**
     * vip会员价
     */
    private Double vipPrice;
    /**
     * 俱乐部负责人价
     */
    private Double clubChargePrice;
    /**
     * 提成比例%
     */
    private Integer percentage;

    /**
     * 是否是成本商品 1.是2.否
     */
    private Integer costGoods;

    /**
     * 销量
     */
    private Integer saleNum;

    /**
     * 排序值 值越大越靠前
     */
    private Integer orderVal;

    /**
     * 上架时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date pushTime;


    /**
     * 产品标签
     */
    private String productTag;

    /**
     * 添加时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date addTime;
}

