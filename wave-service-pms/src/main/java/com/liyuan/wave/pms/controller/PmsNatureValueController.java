package com.liyuan.wave.pms.controller;

import com.liyuan.wave.pms.service.PmsNatureValueService;
import com.liyuan.wavecommon.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @description pms_nature_value
 *
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-15 16:10:20
 */
@RestController
@RequestMapping("pms/nature/value")
public class PmsNatureValueController extends BaseController {

    @Autowired
    private PmsNatureValueService pmsNatureValueService;
}
