package com.langchao.waveservicemall.controller;

import com.chemguan.business.results.JsonResult;
import com.chemguan.business.results.JsonResultGenerator;
import com.chemguan.service.ProductInfoService;
import StringUtils;
import com.langchao.wavecommon.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ws
 * @description TODO
 * @date 2020-03-26
 */
@RestController
@RequestMapping("/product/search")
public class SearchProductController  extends BaseController {

    @Autowired
    private ProductInfoService productInfoService;

    /**
     * @param keywords 关键字
     * @return com.chemguan.business.results.JsonResult
     * @description 根据关键字查询商品
     **/
    @RequestMapping("/getProductsBySearch")
    public JsonResult getProductsBySearch(@RequestParam(required = true) String keywords,
                                      @RequestParam(required = true) Integer pageNum,
                                      @RequestParam(required = true) Integer pageSize) {
        if (StringUtils.isEmpty(keywords)) {
            return JsonResultGenerator.genFailJsonResult("搜索关键字不可为空");
        }
        return success(productInfoService.getProductsbySearch(keywords, pageNum, pageSize));
    }
    /**
     * @description 返回热门搜索关键字 历史搜索待定
     * @param
     * @return
     **/
    @RequestMapping("/getHotSearchKeywords")
    public JsonResult getHotSearchKeywords(){
        return success(productInfoService.getHotSearchKeywords());
    }
}

