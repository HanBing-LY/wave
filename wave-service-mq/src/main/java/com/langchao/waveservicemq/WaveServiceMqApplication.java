package com.langchao.waveservicemq;

import com.langchao.waveservicemq.listener.PageFreemarkerListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
@EnableBinding(value={PageFreemarkerListener.class})
@EnableEurekaClient
public class WaveServiceMqApplication {

	public static void main(String[] args) {
		SpringApplication.run(WaveServiceMqApplication.class, args);
	}

}
