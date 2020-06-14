package com.liyuan.waveservicemall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.liyuan.waveservicemall.mapper")
public class WaveServiceMallApplication {

    public static void main(String[] args) {
        SpringApplication.run(WaveServiceMallApplication.class, args);
    }

}
