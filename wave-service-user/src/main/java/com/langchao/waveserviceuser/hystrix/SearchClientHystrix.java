package com.langchao.waveserviceuser.hystrix;

import com.langchao.wavecommon.vo.response.JsonResult;
import com.langchao.wavepo.user.UserVo;
import com.langchao.waveserviceuser.clients.SearchClient;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class SearchClientHystrix implements SearchClient {

	@Override
	public JsonResult list(UserVo userVo) {
		return null;
	}
}
