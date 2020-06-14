package com.langchao.waveserviceuser.clients;

import com.langchao.waveserviceuser.hystrix.SearchClientHystrix;
import com.liyuan.wave.po.user.UserVo;
import com.liyuan.wavecommon.client.WaveServiceList;
import com.liyuan.wavecommon.vo.response.JsonResult;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = WaveServiceList.WAVE_SERVICE_SEARCH,fallbackFactory = SearchClientHystrix.class)
public interface SearchClient {

	@GetMapping(value="/search/users/list/{page}/{size}")
	@LoadBalanced
	JsonResult list(UserVo userVo);
}

