package com.liyuan.wave.webpage.controller;


import com.liyuan.wave.webpage.service.CmsTemplateService;
import com.liyuan.wave.common.vo.response.JsonResult;
import com.liyuan.wave.common.web.BaseController;
import com.liyuan.waveserviceapi.webpage.CmsTemplateControllerApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cms")
public class CmsTemplateController extends BaseController implements CmsTemplateControllerApi {

    @Autowired
    private CmsTemplateService cmsTemplateService;

    @Override
    @GetMapping("/template/{siteId}")
    public JsonResult findAllCmsTemplate(@PathVariable String siteId) {
        return success( cmsTemplateService.findAllCmsTemplate(siteId));
    }
}
