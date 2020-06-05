package com.langchao.waveservicemall.controller;


import com.langchao.wavecommon.vo.response.JsonResult;
import com.langchao.wavecommon.web.BaseController;
import com.langchao.waveservicemall.service.FlashSaleService;
import com.langchao.waveservicemall.service.ProductInfoService;
import com.langchao.waveservicemall.service.ShopBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liyuan
 */
@RestController
public class ShopHomePageController  extends BaseController {

    @Autowired
    private ShopBannerService shopBannerService;
    @Autowired
    private ProductInfoService productInfoService;
    @Autowired
    private FlashSaleService flashSaleService;
    /**
     * 1-0-0 商城首页轮播图
     * @return
     */
    @GetMapping("/shop/banner/bannerlist")
    public JsonResult listGetAllBannerList(){
        return success(shopBannerService.findAll());
    }
    /**
     * @description 1-0-0 商城首页限时秒杀活动入口
     * @author ws
     * @return
     */
    @GetMapping("/shop/home/flashSale")
    public JsonResult getNowFlashSale(){
        //获取正在进行的秒杀活动
        return success(flashSaleRepository.getCurrentFlashSale());
    }
}
