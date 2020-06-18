package com.liyuan.waveserviceapi.search;


import com.liyuan.wave.po.ucenter.UserVo;
import com.liyuan.wave.common.vo.response.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "用户搜索",description = "用户搜索",tags = {"用户搜索"})
public interface EsUserControllerApi {

    @ApiOperation("用户综合搜索")
    JsonResult list(UserVo userVo);

}
