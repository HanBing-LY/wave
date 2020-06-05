package com.langchao.waveservicemall.pojo.dto;

import com.langchao.waveservicemall.pojo.GroupInfo;
import com.langchao.waveservicemall.pojo.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author liyuan
 * @create 2020-03-19-16:17-周四
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupInfoDTO extends GroupInfo {


    /** 产品id */
    private Integer productInfoId;
    /** 产品名称 */
    private String productName;
    /** 产品售价 */
    private Double productSalePrice;
    /** 拼团价 */
    private Double groupSalePrice;
    /**
     * 俱乐部名称
     */
    private String clubName;
    /**
     * 俱乐部图标
     */
    private String clubIcon;
    /**
     * 拼团结束时间
     */
    private Date endTime;
    /**
     * 距离拼团结束时间的时间戳(毫秒)
     */
    private Long secondToEndTime;

    /**
     * 拼团人数(几人购)
     */
    private Integer clubGroupPeople;

    /**
     * 目前有多少人拼团了
     */
    private Integer clubTotalPeople;

    /**
     * 拼团的成员信息
     */
    private List<UserInfo> userInfoList;
    /**
     * 商品总计拼团人数
     */
    private Integer productGroupTotalPeople;

}
