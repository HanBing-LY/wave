package com.liyuan.wave.pms.po.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author liyuan
 * @description
 * @email 724837404@qq.com
 * @date 2020-06-20-0:05
 */
@Data
public class PmsProductSkuDto {

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 产品id
     */
    private Long productId;
    /**
     * 商品货号
     */
    private String articleNumber;
    /**
     * 产品价格
     */
    private BigDecimal productPrice;
    /**
     * 销量
     */
    private Long saleNum;
}
