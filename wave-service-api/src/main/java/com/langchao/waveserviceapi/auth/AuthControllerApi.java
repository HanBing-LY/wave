package com.langchao.waveserviceapi.auth;


import com.langchao.wavecommon.vo.response.JsonResult;
import com.langchao.wavepo.user.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "用户认证",description = "用户认证接口")
public interface AuthControllerApi {
    @ApiOperation("登录")
    public JsonResult login(User user);

    @ApiOperation("退出")
    public JsonResult logout();

    @ApiOperation("查询用户jwt令牌")
    public JsonResult userjwt();
}
