package com.langchao.waveservicefilesystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class WaveServiceFilesystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(WaveServiceFilesystemApplication.class, args);
	}

}
