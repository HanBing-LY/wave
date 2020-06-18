package com.liyuan.waveservicefilesystem.controller;


import com.liyuan.wave.common.vo.response.JsonResult;
import com.liyuan.wave.common.web.BaseController;
import com.liyuan.waveserviceapi.filesystem.FileSystemControllerApi;
import com.liyuan.waveservicefilesystem.service.FileSystemService;
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