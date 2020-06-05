package com.langchao.waveservicemall.pojo;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.langchao.waveservicemall.pojo.base.BasicPo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author
 * @Title: UserInfo
 * @ProjectName
 * @Description: TODO
 * @date Thu Dec 26 09:52:12 CST 2019
 */

@Data
@TableName("user_info")
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo extends BasicPo implements Serializable {

    /**
     * id
     */
    @TableId
    private Integer id;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 用户头像
     */
    private String userImg;
    /**
     * 微信公众号的openid
     */
    private String openId;
    /**
     * 小程序的openid
     */
    private String miniOpenId;


    /**
     * 1:公众号登陆 2:小程序登陆
     */
    private Integer loginType;

    /**
     * 微信/小程序的unionid
     */
    private String unionId;
    /**
     * 用户积分
     */
    private Integer userScore;
    /**
     * 财务贡献
     */
    private Double contributeMoney;
    /**
     * 赛事能量值
     */
    private Integer matchEnergy;
    /**
     * 添加时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date addtime;
    /**
     * 用户姓名
     */
    private String userName;
    /**
     * 性别
     */
    private String userSex;
    /**
     * 身份证号
     */
    private String certNumber;
    /**
     * 生日
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date birth;
    /**
     * 国家
     */
    private String country;
    /**
     * 所在省
     */
    private String province;
    /**
     * 所在市
     */
    private String district;
    /**
     * 地址
     */
    private String address;
    /**
     * 民族
     */
    private String nation;
    /**
     * 血型
     */
    private String bloodType;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 服装尺寸
     */
    private String clothSize;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 紧急联系人
     */
    private String contactName;
    /**
     * 紧急联系人手机
     */
    private String contactPhone;

    /**
     * 监护人姓名
     */
    private String guardianName;

    /**
     * 监护人身份证号
     */
    private String guardianCertNumber;

    /**
     * 监护人手机号
     */
    private String guardianPhone;

    /**
     * 监护人邮箱
     */
    private String guardianEmail;

    /**
     * 可提现金额
     */
    private Double percentPrice;

    private Integer age;
}

