package com.langchao.waveserviceapi.search;

import com.langchao.wavecommon.vo.response.JsonResult;
import com.langchao.wavepo.user.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@Api(value = "用户搜索",description = "用户搜索",tags = {"用户搜索"})
public interface EsUserControllerApi {

    @ApiOperation("用户综合搜索")
    public JsonResult list(UserVo userVo);

}
