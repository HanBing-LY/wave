package com.liyuan.wave.auth.client;


import com.liyuan.wave.po.ucenter.ext.UserExt;
import com.liyuan.wave.common.client.WaveServiceList;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = WaveServiceList.WAVE_SERVICE_USER_CENTER)
public interface UserClient {

    @GetMapping("/ucenter/getuserext")
    UserExt getUserext(@RequestParam("username") String username);
}
