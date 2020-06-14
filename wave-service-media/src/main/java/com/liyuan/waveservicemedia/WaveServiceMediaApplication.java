package com.liyuan.waveservicemedia;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@MapperScan("com.liyuan.waveservicemedia.mapper")
@EnableDiscoveryClient
public class WaveServiceMediaApplication {

	public static void main(String[] args) {
		SpringApplication.run(WaveServiceMediaApplication.class, args);
	}

}
