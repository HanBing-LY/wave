package com.liyuan.wave.po.ucenter;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@Data
@TableName("permission")
public class Permission implements Serializable {

    @TableId
    private String id;
    private String role_id;
    private String menu_id;
    private Date create_time;


}
