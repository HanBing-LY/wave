package com.liyuan.wave.pms.controller;

import com.liyuan.wave.common.vo.response.JsonResult;
import com.liyuan.wave.common.web.BaseController;
import com.liyuan.wave.pms.service.PmsProductSkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @description pms_product_sku
 *
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-15 16:10:20
 */
@RestController
@RequestMapping("/pms/product/sku")
public class PmsProductSkuController extends BaseController {

    @Autowired
    private PmsProductSkuService pmsProductSkuService;

    /**
     * @description 商品sku对应商品的信息
     * @param articleNumber
     * @return
     */
    @GetMapping
    public JsonResult getDetailByArticleNumber(@RequestParam(name = "articleNumber") String articleNumber){
        return success(pmsProductSkuService.getDetailByArticleNumber(articleNumber));
    }

}
