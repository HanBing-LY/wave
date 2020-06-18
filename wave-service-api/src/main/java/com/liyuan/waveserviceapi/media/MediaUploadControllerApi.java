package com.liyuan.waveserviceapi.media;


import com.liyuan.wave.common.vo.response.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.multipart.MultipartFile;

@Api(value = "媒资管理接口",description = "媒资管理接口，提供文件上传、处理等接口")
public interface MediaUploadControllerApi {

    @ApiOperation("文件上传注册")
    JsonResult register(String fileMd5, String fileName, Long fileSize, String mimetype, String fileExt);

    @ApiOperation("校验分块文件是否存在")
    JsonResult checkChunk(String fileMd5, Integer chunk, Integer chunkSize);

    @ApiOperation("上传分块")
    JsonResult uploadChunk(MultipartFile file, String fileMd5, Integer chunk);

    @ApiOperation("合并分块")
    JsonResult mergeChunks(String fileMd5, String fileName, Long fileSize, String mimetype, String fileExt);

}
