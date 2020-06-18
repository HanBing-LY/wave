package com.liyuan.waveservicemedia.controller;


import com.liyuan.wave.common.vo.response.JsonResult;
import com.liyuan.wave.common.web.BaseController;
import com.liyuan.waveserviceapi.media.MediaUploadControllerApi;
import com.liyuan.waveservicemedia.service.MediaUpLoadService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@RestController
@RequestMapping("/media/upload")
public class MediaUploadController extends BaseController implements MediaUploadControllerApi {

	@Resource
	MediaUpLoadService mediaUpLoadService;

	@Override
	@PostMapping("/register")
	public JsonResult register(String fileMd5, String fileName, Long fileSize, String mimetype, String fileExt) {
		mediaUpLoadService.register(fileMd5,fileName,fileSize,mimetype,fileExt);
		return success();
	}

	@Override
	@PostMapping("/checkchunk")
	public JsonResult checkChunk(String fileMd5, Integer chunk, Integer chunkSize) {
		return success(mediaUpLoadService.checkchunk(fileMd5,chunk,chunkSize));
	}

	@Override
	@PostMapping("/uploadchunk")
	public JsonResult uploadChunk(MultipartFile file, String fileMd5, Integer chunk) {
		mediaUpLoadService.uploadchunk(file,fileMd5,chunk);
		return success();
	}

	@Override
	@PostMapping("/mergechunks")
	public JsonResult mergeChunks(String fileMd5, String fileName, Long fileSize, String mimetype, String fileExt) {
		mediaUpLoadService.mergechunks(fileMd5,fileName,fileSize, mimetype,fileExt);
		return success();
	}

}
