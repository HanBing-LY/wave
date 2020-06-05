package com.langchao.waveservicemedia.controller;


import com.langchao.wavecommon.vo.response.JsonResult;
import com.langchao.wavecommon.web.BaseController;
import com.langchao.wavepo.media.request.QueryMediaFileRequest;
import com.langchao.waveserviceapi.media.MediaFileControllerApi;
import com.langchao.waveservicemedia.service.MediaFileService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/media/file")
public class MediaFileController extends BaseController implements MediaFileControllerApi {

	@Resource
	private MediaFileService mediaFileService;

	@Override
	@GetMapping("/list/{page}/{size}")
	public JsonResult findList(@PathVariable int page, @PathVariable int size, QueryMediaFileRequest queryMediaFileRequest) {
		return success(mediaFileService.queryBy(page,size,queryMediaFileRequest));
	}
}
