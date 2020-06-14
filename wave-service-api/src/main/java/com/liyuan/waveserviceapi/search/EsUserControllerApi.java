package com.liyuan.waveserviceapi.search;


import com.liyuan.wave.po.user.UserVo;
import com.liyuan.wavecommon.vo.response.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "用户搜索",description = "用户搜索",tags = {"用户搜索"})
public interface EsUserControllerApi {

    @ApiOperation("用户综合搜索")
    JsonResult list(UserVo userVo);

}
