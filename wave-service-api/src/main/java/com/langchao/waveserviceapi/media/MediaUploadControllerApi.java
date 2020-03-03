package com.langchao.waveserviceapi.media;


import com.langchao.wavecommon.vo.response.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.multipart.MultipartFile;

@Api(value = "媒资管理接口",description = "媒资管理接口，提供文件上传、处理等接口")
public interface MediaUploadControllerApi {

    @ApiOperation("文件上传注册")
    public JsonResult register(String fileMd5, String fileName, Long fileSize, String mimetype, String fileExt);

    @ApiOperation("校验分块文件是否存在")
    public JsonResult checkchunk(String fileMd5, Integer chunk, Integer chunkSize);

    @ApiOperation("上传分块")
    public JsonResult uploadchunk(MultipartFile file, String fileMd5, Integer chunk);

    @ApiOperation("合并分块")
    public JsonResult mergechunks(String fileMd5, String fileName, Long fileSize, String mimetype, String fileExt);

}
