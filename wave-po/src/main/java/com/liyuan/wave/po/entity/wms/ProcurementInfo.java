package com.liyuan.wave.po.entity.wms;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.liyuan.wave.po.entity.base.BasicPo;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author liyuan
 * @description 商品采购单
 * @email 724837404@qq.com
 * @date 2020-06-11 23:17:44
 */
@Data
@TableName("procurement_info")
public class ProcurementInfo extends BasicPo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId
    private Long id;

    /**
     * 采购单编号
     */
    private Long purchaseNumber;

    /**
     * 采购商品id
     */
    private Long productSkuId;

    /**
     * 采购数量
     */
    private Long skuNum;

    /**
     * 采购金额
     */
    private BigDecimal skuPrice;

    /**
     * 状态[0新建，1已分配，2正在采购，3已完成，4采购失败]
     */
    private Byte status;

}
