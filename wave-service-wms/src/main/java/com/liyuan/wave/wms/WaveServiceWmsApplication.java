package com.liyuan.wave.wms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @description 仓储
 *
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-11 23:17:44
 */
@EnableCircuitBreaker
@EnableDiscoveryClient
@SpringBootApplication
public class WaveServiceWmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(WaveServiceWmsApplication.class, args);
    }

}
