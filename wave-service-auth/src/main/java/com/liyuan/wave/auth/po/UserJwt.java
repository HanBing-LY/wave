package com.liyuan.wave.auth.po;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Date;

@Data
public class UserJwt extends User {
    private String id;
    private String username;
    private String password;
    private String salt;
    private String name;
    private String utype;
    private String birthday;
    private String userpic;
    private String sex;
    private String email;
    private String phone;
    private String status;
    private Date createTime;
    private Date updateTime;
    private String companyId;

    public UserJwt(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }
}
