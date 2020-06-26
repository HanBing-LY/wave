package com.liyuan.wave.sms.client;

import com.liyuan.wave.common.client.WaveServiceList;
import com.liyuan.wave.common.vo.response.JsonResult;
import com.liyuan.wave.sms.client.fallback.PmsProductSkuClientFallback;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author liyuan
 * @description
 * @email 724837404@qq.com
 * @date 2020-06-20-0:27
 */
@Component
@FeignClient(value = WaveServiceList.WAVE_SERVICE_PMS, fallback = PmsProductSkuClientFallback.class)
public interface PmsProductSkuClient {

    /**
     * @param articleNumber
     * @return
     * @description 商品sku对应商品的信息
     */
    @HystrixCommand(fallbackMethod = "PmsProductSkuClientFallback.getDetailByArticleNumber", commandProperties = {
            // 服务降级
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5000"),
            // 是否开启断路器
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
            // 请求数达到后才计算
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
            // 休眠时间窗
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
            // 错误率达到多少跳闸
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")
    })
    @GetMapping("pms/product/sku")
    JsonResult getDetailByArticleNumber(@RequestParam(name = "articleNumber") String articleNumber);
}
