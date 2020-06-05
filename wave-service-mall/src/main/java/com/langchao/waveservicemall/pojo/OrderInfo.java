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
 * @Title: OrderInfo
 * @ProjectName
 * @Description: TODO
 * @date Thu Dec 26 09:52:11 CST 2019
 */
@Data
@TableName("order_info")
@NoArgsConstructor
@AllArgsConstructor
public class OrderInfo extends BasicPo implements Serializable {

    /**
     * id
     */
    @TableId
    private Integer id;
    /**
     * 订单编号
     */
    private String orderNumber;
    /**
     * 支付订单编号
     */
    private String payNumber;
    /**
     * 订单金额
     */
    private Double orderMoney;
    /**
     * 运费
     */
    private Double fareMoney;
    /**
     * 订单实付金额
     */
    private Double payMoney;
    /**
     * 订单实付积分
     */
    private Integer payScore;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 收货人
     */
    private String receiveName;
    /**
     * 手机号
     */
    private String receivePhone;
    /**
     * 省
     */
    private String province;
    /**
     * 市
     */
    private String district;
    /**
     * 区
     */
    private String area;
    /**
     * 详细地址
     */
    private String address;
    /**
     * 下单时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date addtime;
    /**
     * 1:待付款 2:待发货 3:已发货 4:已退款
     */
    private String orderType;
    /**
     * 支付方式1:微信 2:银联 3:积分，多个以逗号隔开
     */
    private String payMethod;
    /**
     * 物流单号
     */
    private String logisticsNum;

}

