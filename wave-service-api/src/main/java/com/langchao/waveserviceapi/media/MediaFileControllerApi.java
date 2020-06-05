package com.langchao.waveserviceapi.media;


import com.langchao.wavecommon.vo.response.JsonResult;
import com.langchao.wavepo.media.request.QueryMediaFileRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Created by Administrator.
 */
@Api(value = "媒体文件管理",tags = {"媒体文件管理接口"})
public interface MediaFileControllerApi {

    @ApiOperation("我的媒资文件查询列表")
    JsonResult findList(int page, int size, QueryMediaFileRequest queryMediaFileRequest);

}

