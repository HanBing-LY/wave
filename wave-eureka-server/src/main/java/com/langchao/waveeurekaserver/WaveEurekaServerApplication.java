package com.langchao.waveeurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class WaveEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(WaveEurekaServerApplication.class, args);
	}

}
