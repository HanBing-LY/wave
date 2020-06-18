package com.liyuan.waveservicemedia.controller;


import com.liyuan.wave.po.media.request.QueryMediaFileRequest;
import com.liyuan.wave.common.vo.response.JsonResult;
import com.liyuan.wave.common.web.BaseController;
import com.liyuan.waveserviceapi.media.MediaFileControllerApi;
import com.liyuan.waveservicemedia.service.MediaFileService;
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
