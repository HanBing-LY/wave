package com.langchao.waveservicemall.pojo.vo;

import com.chemguan.entity.ColumnNatureDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author liyuan
 * @create 2020-03-20-20:12-周五
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailVo implements Serializable {
    /**
     * 订单单系列商品id
     */
    private Integer id;
    /**
     * 订单编号
     */
    private Integer orderNumber;
    /**
     * 订单金额
     */
    private Double orderMoney;
    /**
     * 运费
     */
    private Double fareMoney;
    /**
     * 收货人姓名
     */
    private String receiveName;
    /**
     * 收货电话
     */
    private Double receivePhone;
    /**
     * 省
     */
    private Double province;
    /**
     * 市
     */
    private Double district;
    /**
     * 区
     */
    private Double area;
    /**
     * 详细地址
     */
    private Double address;

    /**
     * 商品id
     */
    private Integer productId ;

    /**
     * 商品名称
     */
    private  String productName;
    /**
     * 商品图片
     */
    private  String productPictureUrl;
    /**
     * 商品购买数量
     */
    private Integer productNum;
    /**
     * 商品购买价格
     */
    private Double productPrice;
    /**
     * 属性list
     */
    private List<ColumnNatureDTO> columnNatureDTOList;

}
