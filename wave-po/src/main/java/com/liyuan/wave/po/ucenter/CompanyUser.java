package com.liyuan.wave.po.ucenter;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;


@Data
@TableName("company_user")
public class CompanyUser implements Serializable {
    @TableId
    private String id;
    private String companyId;
    private String userId;


}
