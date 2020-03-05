package com.langchao.waveservicemedia;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.langchao.waveservicemedia.mapper")
public class WaveServiceMediaApplication {

	public static void main(String[] args) {
		SpringApplication.run(WaveServiceMediaApplication.class, args);
	}

}
