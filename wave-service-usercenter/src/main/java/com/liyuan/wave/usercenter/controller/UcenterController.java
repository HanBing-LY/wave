package com.liyuan.wave.usercenter.controller;


import com.liyuan.wave.po.ucenter.ext.UserExt;
import com.liyuan.wave.usercenter.service.UserService;
import com.liyuan.wave.common.web.BaseController;
import com.liyuan.waveserviceapi.ucenter.UcenterControllerApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ucenter")
public class UcenterController extends BaseController implements UcenterControllerApi {

    @Autowired
    private UserService userService;

    @Override
    @GetMapping("/getuserext")
    public UserExt getUserext(String username) {
        return userService.getUserext(username);
    }
}
