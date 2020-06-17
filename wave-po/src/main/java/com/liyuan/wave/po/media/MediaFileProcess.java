package com.liyuan.wave.po.media;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author: mrt.
 * @description:
 * @Date:Created in 2018/1/24 10:04.
 * @Modified By:
 */
@Data
@ToString
public class MediaFileProcess {

    //错误信息
    private String errormsg;

    //ts列表
    private List<String> tslist;
}
