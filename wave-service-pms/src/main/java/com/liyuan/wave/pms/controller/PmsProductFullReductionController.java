package com.liyuan.wave.pms.controller;


import com.liyuan.wave.pms.service.PmsProductFullReductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description
 *
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-11 23:17:44
 */
@RestController
@RequestMapping("wave/pmsproductfullreduction")
public class PmsProductFullReductionController {
    @Autowired
    private PmsProductFullReductionService pmsProductFullReductionService;
}
