package com.langchao.waveservicemall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.langchao.waveservicemall.mapper")
public class WaveServiceMallApplication {

    public static void main(String[] args) {
        SpringApplication.run(WaveServiceMallApplication.class, args);
    }

}
