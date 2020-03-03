package com.langchao.waveserviceuser.hystrix;

import org.springframework.stereotype.Component;

@Component
public class MyHystrix {

	public String dead(){
		return "充钱没?充8W";
	}
}
