package com.liyuan.waveserviceapi.webpage;


import com.liyuan.wave.common.vo.response.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="cms模板管理接口",tags = "cms模板管理接口")
public interface CmsTemplateControllerApi {

    //查询站点信息
    @ApiOperation("查询模板信息")
    JsonResult findAllCmsTemplate(String siteId);
}
