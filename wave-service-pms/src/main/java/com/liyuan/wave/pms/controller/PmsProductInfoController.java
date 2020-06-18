package com.liyuan.wave.pms.controller;

import com.liyuan.wave.pms.service.PmsProductInfoService;
import com.liyuan.wave.common.vo.response.JsonResult;
import com.liyuan.wave.common.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author liyuan
 * @description pms_product_info
 * @email 724837404@qq.com
 * @date 2020-06-15 16:10:20
 */
@RestController
@RequestMapping("pms/product")
public class PmsProductInfoController extends BaseController {

    @Autowired
    private PmsProductInfoService pmsProductInfoService;

    /**
     * @return
     * @description 查询所有的五级分类下的所有商品
     */
    @GetMapping("/bycolumn")
    public JsonResult listGetAllProductsByMinColumn(@RequestParam(name = "id") Long id,@RequestParam(name = "page", required = false) Integer pageNum, @RequestParam(name = "size", required = false) Integer pageSize) {
        return success(pmsProductInfoService.listGetAllProductsByMinColumn(id,pageNum, pageSize));
    }


    /**
     * @param pageNum
     * @param pageSize
     * @return
     * @description 热销商品
     */
    @GetMapping("/hotsalelist")
    public JsonResult listHotSaleList(@RequestParam(name = "page", required = false) Integer pageNum, @RequestParam(name = "size", required = false) Integer pageSize) {
        return success(pmsProductInfoService.listHotSaleList(pageNum, pageSize));
    }

    /**
     * @param pageNum
     * @param pageSize
     * @return
     * @description 热搜商品
     */
    @GetMapping("/hotsearchlist")
    public JsonResult hotSearchList(@RequestParam(name = "page", required = false) Integer pageNum, @RequestParam(name = "size", required = false) Integer pageSize) {
        return success(pmsProductInfoService.hotSearchList(pageNum, pageSize));
    }

    /**
     * @param pageNum
     * @param pageSize
     * @return
     * @description 最新上架商品
     */
    @GetMapping("/newpushlist")
    public JsonResult listNewPushList(@RequestParam(name = "page", required = false) Integer pageNum, @RequestParam(name = "size", required = false) Integer pageSize) {
        return success(pmsProductInfoService.listNewPushList(pageNum, pageSize));
    }

    /**
     * @param pageNum
     * @param pageSize
     * @return
     * @description 明星商品
     */
    @GetMapping("/star/list")
    public JsonResult starList(@RequestParam(name = "page", required = false) Integer pageNum, @RequestParam(name = "size", required = false) Integer pageSize) {
        return success(pmsProductInfoService.starList(pageNum, pageSize));
    }

}
