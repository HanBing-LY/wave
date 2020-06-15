package com.liyuan.wave.po.ucenter.ext;


import com.liyuan.wave.po.ucenter.Menu;
import com.liyuan.wave.po.ucenter.User;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class UserExt extends User {

    //权限信息
    private List<Menu> permissions;

    //企业信息
    private String companyId;
}
