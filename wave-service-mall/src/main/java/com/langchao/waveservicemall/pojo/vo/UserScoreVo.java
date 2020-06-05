package com.langchao.waveservicemall.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author ws
 * @description 返回用户积分相关信息
 * @date 2020-03-20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserScoreVo implements Serializable {
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 用户头像
     */
    private String userImg;
    /**
     * 用户积分
     */
    private Integer userScore;
    /**
     *是否为vip
     */
    private Boolean isVipUser;
    /**
     *个人财务贡献
     */
    private Double userContributeMoney;
    /**
     *是否为资深俱乐部负责人
     */
    private Boolean isVipClubLeader;
    /**
     *俱乐部财务贡献
     */
    private Double clubConsumMoney;
    /**
     * 赛事能量值
     */
    private Integer matchEnergy;
}
