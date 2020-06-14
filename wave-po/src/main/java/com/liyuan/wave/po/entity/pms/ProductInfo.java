package com.liyuan.wave.po.entity.pms;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.liyuan.wave.po.entity.base.BasicPo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author liyuan
 * @description 商品基本信息
 * @email 724837404@qq.com
 * @date 2020-06-11 23:17:47
 */
@Data
@TableName("product_info")
@NoArgsConstructor
@AllArgsConstructor
public class ProductInfo extends BasicPo implements Serializable {

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

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
     * 产品介绍视频
     */
    private String productVideo;

    /**
     * 产品原价（默认展示价格）
     */
    private BigDecimal productOldPrice;

    /**
     * 产品特价
     */
    private BigDecimal productSalePrice;

    /**
     * 产品详情
     */
    private String productDesc;

    /**
     * 评论次数
     */
    private Long commentsCount;

    /**
     * 浏览次数
     */
    private Long viewed;

    /**
     * 交易次数
     */
    private Long purchaseCount;

    /**
     * 1:上架 0:下架
     */
    private Byte type;

    /**
     * 最小产品分类id
     */
    private Long productColumnId;

    /**
     * 销量
     */
    private Long saleNum;

    /**
     * 排序值 值越大越靠前
     */
    private Long orderVal;

    /**
     * 上架时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date pushTime;

    /**
     * 产品标签
     */
    private String productTag;

}

