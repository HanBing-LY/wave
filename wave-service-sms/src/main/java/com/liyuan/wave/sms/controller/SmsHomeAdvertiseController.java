package com.liyuan.wave.sms.controller;


import com.liyuan.wave.sms.service.SmsHomeAdvertiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description
 *
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-11 23:17:47
 */
@RestController
@RequestMapping("wave/smshomeadvertise")
public class SmsHomeAdvertiseController {
    @Autowired
    private SmsHomeAdvertiseService smsHomeAdvertiseService;
}
