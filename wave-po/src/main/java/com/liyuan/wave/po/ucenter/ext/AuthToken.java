package com.liyuan.wave.po.ucenter.ext;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthToken {
    String access_token;//访问token
    String refresh_token;//刷新token
    String jwt_token;//jwt令牌
}
