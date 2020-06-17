package com.liyuan.wave.pms.controller;

import com.liyuan.wave.pms.po.vo.PmsProductSkuNatureVo;
import com.liyuan.wave.pms.service.PmsProductSkuNatureService;
import com.liyuan.wavecommon.vo.response.JsonResult;
import com.liyuan.wavecommon.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @description pms_product_sku_nature
 *
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-15 16:10:20
 */
@RestController
@RequestMapping("pms/pmsproductskunature")
public class PmsProductSkuNatureController extends BaseController {

    @Autowired
    private PmsProductSkuNatureService pmsProductSkuNatureService;

    /**
     * @description 商品选择具有的sku属性值
     * @param pmsProductSkuNatureVo
     * @return
     */
    @PostMapping("/save")
    public JsonResult saveProductSkuNatureValue(@RequestBody PmsProductSkuNatureVo pmsProductSkuNatureVo){
        pmsProductSkuNatureService.saveProductSkuNatureValue(pmsProductSkuNatureVo);
        return success();
    }

}
