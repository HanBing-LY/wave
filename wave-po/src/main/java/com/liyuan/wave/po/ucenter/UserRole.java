package com.liyuan.wave.po.ucenter;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("user_role")
public class UserRole implements Serializable {

    @TableId
    private String id;

    private String userId;
    private String roleId;
    private String creator;
    private Date createTime;

}
