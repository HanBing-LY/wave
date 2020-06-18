package com.liyuan.waveserviceapi.filesystem;


import com.liyuan.wave.common.vo.response.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.multipart.MultipartFile;

@Api(value = "文件管理接口", tags = "文件管理接口，提供文件的增、删、改、查")
public interface FileSystemControllerApi {

    @ApiOperation("上传文件接口")
    JsonResult upload(MultipartFile multipartFile);
}