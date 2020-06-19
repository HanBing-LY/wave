package com.liyuan.wave.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @description 统一定时任务
 * @author liyuan
 * @date
 */
@SpringBootApplication
@EnableScheduling
@EnableDiscoveryClient
public class WaveServiceTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(WaveServiceTaskApplication.class, args);
    }

}
