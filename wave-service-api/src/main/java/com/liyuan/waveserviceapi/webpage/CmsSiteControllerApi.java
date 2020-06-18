package com.liyuan.waveserviceapi.webpage;


import com.liyuan.wave.common.vo.response.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="cms站点管理接口",tags = "cms站点管理接口")
public interface CmsSiteControllerApi {

    //查询站点信息
    @ApiOperation("查询站点信息")
    JsonResult findAllCmsSite();
}
