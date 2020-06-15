package com.liyuan.waveserviceapi.auth;


import com.liyuan.wave.po.ucenter.request.LoginRequest;
import com.liyuan.wavecommon.vo.response.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Created by Administrator.
 */
@Api(value = "用户认证",description = "用户认证接口")
public interface AuthControllerApi {
    @ApiOperation("登录")
    JsonResult login(LoginRequest loginRequest);

    @ApiOperation("退出")
    JsonResult logout();

    @ApiOperation("查询用户jwt令牌")
    JsonResult userjwt();
}
