package com.liyuan.wave.oms.client;

import com.liyuan.wave.common.vo.response.JsonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author liyuan
 * @description
 * @date 2020-06-15 15:47
 */
@Component
@FeignClient
public interface UmsUserAddressClient {

    /**
     * @param id
     * @return
     * @description 查询地址
     */
    @GetMapping
    JsonResult selectById(@RequestParam Long id);
}
