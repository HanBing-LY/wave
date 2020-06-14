package com.liyuan.wave.po.entity.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author liyuan
 * @description base
 * @date 2020-01-05 13:54
 */
@Data
public class BasicPo implements Serializable {

    /**
     * 是否删除: 默认值0;删除:1
     */
    private Byte del;

    /**
     * 上次修改人
     */
    private String modifyName;

    /**
     * 创建人
     */
    private String createName;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
}
