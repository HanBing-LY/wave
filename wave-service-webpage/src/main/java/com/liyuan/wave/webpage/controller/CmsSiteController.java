package com.liyuan.wave.webpage.controller;


import com.liyuan.wave.webpage.service.CmsSiteService;
import com.liyuan.wave.common.vo.response.JsonResult;
import com.liyuan.wave.common.web.BaseController;
import com.liyuan.waveserviceapi.webpage.CmsSiteControllerApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cms")
public class CmsSiteController extends BaseController implements CmsSiteControllerApi {

    @Autowired
    private CmsSiteService cmsSiteService;

    @Override
    @GetMapping("/site")
    public JsonResult findAllCmsSite() {
        return success(cmsSiteService.list());
    }
}
