package com.liyuan.wave.wms.feign;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("gulimall-product")
public interface ProductFeignService {
}
