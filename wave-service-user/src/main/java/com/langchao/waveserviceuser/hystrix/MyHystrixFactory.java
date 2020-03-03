package com.langchao.waveserviceuser.hystrix;

import com.langchao.wavecommon.vo.response.JsonResult;
import com.langchao.waveserviceuser.clients.SearchClient;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class MyHystrixFactory implements FallbackFactory<SearchClient> {

	@Override
	public SearchClient create(Throwable throwable) {
		return userVo -> new JsonResult();
	}
}
