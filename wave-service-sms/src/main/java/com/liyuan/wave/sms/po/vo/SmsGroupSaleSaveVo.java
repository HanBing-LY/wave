package com.liyuan.wave.sms.po.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author liyuan
 * @description
 * @email 724837404@qq.com
 * @date 2020-06-17-23:31
 */
@Data
public class SmsGroupSaleSaveVo {

    /**
     * id
     */
    private Long id;
    /**
     * 控制开关 1:开启 0:关闭
     */
    private Byte flag;
    /**
     * 主题
     */
    private String note;
    /**
     * 描述
     */
    private String message;
    /**
     * 开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startTime;
    /**
     * 结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;

}
