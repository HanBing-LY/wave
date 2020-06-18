package com.liyuan.waveserviceapi.media;


import com.liyuan.wave.common.vo.response.JsonResult;
import com.liyuan.wave.po.media.request.QueryMediaFileRequest;
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

