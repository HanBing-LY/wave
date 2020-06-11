package com.liyuan.wave.ums.controller;


import com.liyuan.wave.ums.service.UmsMemberRuleSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description
 *
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-11 23:17:45
 */
@RestController
@RequestMapping("wave/umsmemberrulesetting")
public class UmsMemberRuleSettingController {
    @Autowired
    private UmsMemberRuleSettingService umsMemberRuleSettingService;


}
