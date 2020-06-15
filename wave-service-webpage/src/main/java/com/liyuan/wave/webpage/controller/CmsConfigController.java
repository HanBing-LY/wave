package com.liyuan.wave.webpage.controller;


import com.liyuan.wave.po.webpage.cms.CmsConfig;
import com.liyuan.wave.webpage.service.CmsConfigService;
import com.liyuan.wavecommon.web.BaseController;
import com.liyuan.waveserviceapi.webpage.CmsConfigControllerApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cms/config")
public class CmsConfigController extends BaseController implements CmsConfigControllerApi {

    @Autowired
    private CmsConfigService cmsConfigService;

    @Override
    @GetMapping("/getmodel/{id}")
    public CmsConfig getmodel(@PathVariable String id) {
        return cmsConfigService.getCmsConfigAndModel(id);
    }
}
