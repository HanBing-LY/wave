package com.langchao.waveservicemall.pojo.vo;

import com.chemguan.entity.UserAddress;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ws
 * @description 返回确认订单需要的信息 如订单金额、订单编号、默认配送地址等
 * @date 2020-03-23
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScoreOrderInfoVo implements Serializable {

    /**
     * 积分商品名称
     */
    private String productName;
    /**
     * 产品封面图
     */
    private String productIcon;
    /**
     * 订单实付金额
     */
    private Double payMoney;
    /**
     * 订单实付积分
     */
    private Integer payScore;
    /**
     * 运费
     */
    private Double fareMoney;
    /**
     * 订单编号
     */
    private String orderNumber;
    /**
     * 收货人
     */
    private String receiveName;
    /**
     * 收货手机号
     */
    private String receivePhone;
    /**
     * 收货手机号
     */
    private String orderType;
    /**
     * 省
     */
    private String province;
    /**
     * 省
     */
    private String district;
    /**
     * 省区
     */
    private String area;
    /**
     * 详细地址
     */
    private String address;
    /**
     * 下单时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date addtime;


}
