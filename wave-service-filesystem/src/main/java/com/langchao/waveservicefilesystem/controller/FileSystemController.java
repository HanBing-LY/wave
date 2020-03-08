package com.langchao.waveservicefilesystem.controller;


import com.langchao.wavecommon.vo.response.JsonResult;
import com.langchao.wavecommon.web.BaseController;
import com.langchao.waveserviceapi.filesystem.FileSystemControllerApi;
import com.langchao.waveservicefilesystem.service.FileSystemService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@RestController
@RequestMapping("/filesystem")
public class FileSystemController extends BaseController implements FileSystemControllerApi {
    @Resource
    private FileSystemService fileSystemService;

    @Override
    @PostMapping("/upload")
    public JsonResult upload(MultipartFile multipartFile) {
        return success("上传成功",fileSystemService.upload(multipartFile));
    }
}