package com.langchao.waveserviceuser.hystrix;

import com.langchao.waveserviceuser.clients.SearchClient;
import com.liyuan.wave.po.user.UserVo;
import com.liyuan.wavecommon.vo.response.JsonResult;
import org.springframework.stereotype.Component;

@Component
public class SearchClientHystrix implements SearchClient {

	@Override
	public JsonResult list(UserVo userVo) {
		return null;
	}
}
