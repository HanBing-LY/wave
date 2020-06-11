package com.liyuan.wave.sms.controller;


import com.liyuan.wave.sms.service.SmsHomeNewProductService;
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
@RequestMapping("wave/smshomenewproduct")
public class SmsHomeNewProductController {
    @Autowired
    private SmsHomeNewProductService smsHomeNewProductService;

}
