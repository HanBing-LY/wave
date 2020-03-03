package com.langchao.waveserviceuser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableCircuitBreaker//熔断注解
@EnableDiscoveryClient//服务注册注解
@SpringBootApplication
public class WaveServiceUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(WaveServiceUserApplication.class, args);
	}

}
