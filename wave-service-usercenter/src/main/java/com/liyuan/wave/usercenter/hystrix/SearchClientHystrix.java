package com.liyuan.wave.usercenter.hystrix;

import com.liyuan.wave.usercenter.clients.SearchClient;
import com.liyuan.wave.po.ucenter.UserVo;
import com.liyuan.wave.common.vo.response.JsonResult;
import org.springframework.stereotype.Component;

@Component
public class SearchClientHystrix implements SearchClient {

	@Override
	public JsonResult list(UserVo userVo) {
		return null;
	}
}
