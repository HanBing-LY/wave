package com.langchao.waveservicemedia.controller;


import com.langchao.wavecommon.vo.response.JsonResult;
import com.langchao.wavecommon.web.BaseController;
import com.langchao.waveserviceapi.media.MediaUploadControllerApi;
import com.langchao.waveservicemedia.service.MediaService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@RestController
@RequestMapping("/media/upload")
public class MediaUploadController extends BaseController implements MediaUploadControllerApi {

	@Resource
	MediaService mediaService;

	@Override
	@PostMapping("/register")
	public JsonResult register(String fileMd5, String fileName, Long fileSize, String mimetype, String fileExt) {
		mediaService.register(fileMd5,fileName,fileSize,mimetype,fileExt);
		return success();
	}

	@Override
	@PostMapping("/checkchunk")
	public JsonResult checkchunk(String fileMd5, Integer chunk, Integer chunkSize) {
		return success(mediaService.checkchunk(fileMd5,chunk,chunkSize));
	}

	@Override
	@PostMapping("/uploadchunk")
	public JsonResult uploadchunk(MultipartFile file, String fileMd5, Integer chunk) {
		mediaService.uploadchunk(file,fileMd5,chunk);
		return success();
	}

	@Override
	@PostMapping("/mergechunks")
	public JsonResult mergechunks(String fileMd5, String fileName, Long fileSize, String mimetype, String fileExt) {
		mediaService.mergechunks(fileMd5,fileName,fileSize, mimetype,fileExt);
		return success();
	}

}
