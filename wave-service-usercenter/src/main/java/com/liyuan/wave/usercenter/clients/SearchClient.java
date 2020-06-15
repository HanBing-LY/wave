package com.liyuan.wave.usercenter.clients;

import com.liyuan.wave.usercenter.hystrix.SearchClientHystrix;
import com.liyuan.wave.po.ucenter.UserVo;
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

