package com.langchao.waveservicesearch.controller;


import com.langchao.wavecommon.vo.response.JsonResult;
import com.langchao.wavecommon.web.BaseController;
import com.langchao.wavepo.user.UserVo;
import com.langchao.waveserviceapi.search.EsUserControllerApi;
import com.langchao.waveservicesearch.service.EsUserService;
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