package com.langchao.waveservicemall.controller;

import com.chemguan.business.results.JsonResult;
import com.chemguan.business.results.JsonResultGenerator;
import com.chemguan.entity.ShopBanner;
import com.chemguan.service.ShopBannerService;
import com.langchao.wavecommon.web.BaseController;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("shop/banner")
public class ShopBannerController  extends BaseController {
    @Autowired
    private ShopBannerService shopBannerService;

    /**
    *@Description 后台添加
    *@Author Renjinliang
    *@date 2020/3/27 9:00
    */
    @PostMapping
    public JsonResult add(@RequestBody ShopBanner shopBanner){
        shopBannerService.save(shopBanner);
        return success();
    }

    /**
    *@Description 后台批量删除
    *@Author Renjinliang
    *@date 2020/3/27 9:01
    */
    @GetMapping("delpl")
    public JsonResult delpl(@RequestParam("checkIds") String checkIds) {
        if(StringUtils.isEmpty(checkIds)){
            return JsonResultGenerator.genFailJsonResult("数据为空!");
        }
        try{
            shopBannerService.deleteByIds(checkIds);
        }catch (Exception e){
            return JsonResultGenerator.genFailJsonResult("所选数据已被关联无法删除");
        }
        return success();
    }

    /**
    *@Description 后台修改
    *@Author Renjinliang
    *@date 2020/3/27 9:04
    */
    @PutMapping("/update")
    public JsonResult update(@RequestBody ShopBanner shopBanner){
        shopBannerService.update(shopBanner);
        return success();
    }

    /**
    *@Description 后台详情
    *@Author Renjinliang
    *@date 2020/3/27 9:05
    */
    @GetMapping("/{id}")
    public JsonResult detail(@PathVariable Integer id) {
        return success(shopBannerService.findById(id));
    }

    @GetMapping
    public JsonResult list(
            @RequestParam(name = "page",defaultValue = "1") Integer page,
            @RequestParam(name = "size",defaultValue = "10") Integer size){
        return success(shopBannerService.findManagerList(page, size));
    }
}
