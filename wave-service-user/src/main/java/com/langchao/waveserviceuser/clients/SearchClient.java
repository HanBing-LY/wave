package com.langchao.waveserviceuser.clients;

import com.langchao.wavecommon.client.WaveServiceList;
import com.langchao.wavecommon.vo.response.JsonResult;
import com.langchao.wavepo.user.UserVo;
import com.langchao.waveserviceuser.hystrix.SearchClientHystrix;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = WaveServiceList.WAVE_SERVICE_SEARCH,fallbackFactory = SearchClientHystrix.class)
public interface SearchClient {

	@GetMapping(value="/search/users/list/{page}/{size}")
	@LoadBalanced
	JsonResult list(UserVo userVo);
}

