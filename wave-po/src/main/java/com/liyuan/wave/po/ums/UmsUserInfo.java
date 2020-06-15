package com.liyuan.wave.po.ums;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.liyuan.wave.po.base.BasicPo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author liyuan
 * @description 用户信息
 * @email 724837404@qq.com
 * @date 2020-06-11 23:17:47
 */
@Data
@TableName("ums_user_info")
@NoArgsConstructor
@AllArgsConstructor
public class UmsUserInfo extends BasicPo implements Serializable {

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 用户姓名
     */
    private String userName;

    /**
     * 用户头像
     */
    private String userImg;

    /**
     * 性别
     */
    private String userSex;

    /**
     * 身份证号
     */
    private String certNumber;

    /**
     * 年龄
     */
    private Short age;

    /**
     * 生日
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date birthday;

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
     * 区
     */
    private String area;

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
    private String mobilePhone;

    /**
     * 用户积分
     */
    private Long userScore;

    /**
     * 财务贡献
     */
    private BigDecimal contributeMoney;

    /**
     * 邮箱
     */
    private String email;
}

