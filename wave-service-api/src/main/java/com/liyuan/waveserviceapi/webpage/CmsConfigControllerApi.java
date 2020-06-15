package com.liyuan.waveserviceapi.webpage;


import com.liyuan.wave.po.webpage.cms.CmsConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="cms配置管理接口",tags = "cms配置管理接口")
public interface CmsConfigControllerApi {
    @ApiOperation("根据id查询CMS配置信息")
    CmsConfig getmodel(String id);
}
