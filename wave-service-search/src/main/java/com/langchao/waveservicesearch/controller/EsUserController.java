package com.langchao.waveservicesearch.controller;


import com.langchao.waveservicesearch.service.EsUserService;
import com.liyuan.wave.po.user.UserVo;
import com.liyuan.wavecommon.vo.response.JsonResult;
import com.liyuan.wavecommon.web.BaseController;
import com.liyuan.waveserviceapi.search.EsUserControllerApi;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/search/users")
public class EsUserController extends BaseController implements EsUserControllerApi {
    @Resource
    EsUserService esUserService;

    @Override
    @GetMapping(value="/list/{page}/{size}")
    public JsonResult list(UserVo userVo) {
        return success(esUserService.list(userVo));
    }

}