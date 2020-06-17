package com.liyuan.wave.pms.controller;

import java.util.Arrays;
import java.util.Map;

import com.liyuan.wave.pms.service.PmsProductColumnService;
import com.liyuan.wave.po.pms.PmsProductColumn;
import com.liyuan.wavecommon.vo.response.JsonResult;
import com.liyuan.wavecommon.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @description pms_product_column
 *
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-15 16:10:20
 */
@RestController
@RequestMapping("pms/pmsproductcolumn")
public class PmsProductColumnController extends BaseController {

    @Autowired
    private PmsProductColumnService pmsProductColumnService;

    @PostMapping("/save")
    public JsonResult add(@RequestBody PmsProductColumn pmsProductColumn){
        pmsProductColumnService.add(pmsProductColumn);
        return success();
    }

}
