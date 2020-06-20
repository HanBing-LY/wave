package com.liyuan.wave.sms.po.vo;

import lombok.Data;

/**
 * @author liyuan
 * @description
 * @date 2020-06-20 11:37
 */
@Data
public class SmsGroupInfoVo {


    /**
     * 团编码，唯一标识
     */
    private String groupNumber;

    /**
     * 当前人数
     */
    private Long people;

    /**
     * 发起人
     */
    private Long userName;

    /**
     * 头像
     */
    private Long userIcon;
}
