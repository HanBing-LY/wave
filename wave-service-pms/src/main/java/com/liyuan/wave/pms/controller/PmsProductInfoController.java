package com.liyuan.wave.pms.controller;

import com.liyuan.wave.pms.service.PmsProductInfoService;
import com.liyuan.wavecommon.vo.response.JsonResult;
import com.liyuan.wavecommon.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * @description pms_product_info
 *
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-15 16:10:20
 */
@RestController
@RequestMapping("pms/product")
public class PmsProductInfoController extends BaseController {

    @Autowired
    private PmsProductInfoService pmsProductInfoService;

    /**
     * @description 查询所有的五级分类下的所有商品
     * @return
     */
    @GetMapping("/bycolumn")
    public JsonResult listGetAllProductsByMinColumn(@RequestParam(name = "id") Long id){
        return success(pmsProductInfoService.listGetAllProductsByMinColumn(id));
    }


    /**
     * @description 1-0-0 商城首页热销商品
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/hotsalelist")
    public JsonResult listHotSaleList(@RequestParam(name = "page", required = false) Integer pageNum, @RequestParam(name = "size", required = false) Integer pageSize) {
        return success(pmsProductInfoService.listHotSaleList(pageNum, pageSize));
    }

    /**
     * 1-0-0 商城首页最新上架商品
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/newpushlist")
    public JsonResult listNewPushList(@RequestParam(name = "page", required = false) Integer pageNum, @RequestParam(name = "size", required = false) Integer pageSize) {
        return success(pmsProductInfoService.listNewPushList(pageNum, pageSize));
    }

}
