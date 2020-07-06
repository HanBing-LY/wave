package com.liyuan.wave.oms.controller;

import com.liyuan.wave.common.vo.response.JsonResult;
import com.liyuan.wave.common.web.BaseController;
import com.liyuan.wave.oms.service.OmsShoppingCartInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author liyuan
 * @description oms_shopping_cart_info
 * @email 724837404@qq.com
 * @date 2020-06-15 16:11:12
 */
@RestController
@RequestMapping("oms/shoppingcartinfo")
public class OmsShoppingCartInfoController extends BaseController {

    @Autowired
    private OmsShoppingCartInfoService omsShoppingCartInfoService;

    /**
     * @description 展示购物车
     * @param shoppingCartNumber
     * @return
     */
    @GetMapping
    public JsonResult showShoppingCar(@RequestParam String shoppingCartNumber) {
        return success(omsShoppingCartInfoService.showShoppingCar(shoppingCartNumber));
    }

    /**
     * @description 移除某个购物车商品
     * @param articleNumbers
     * @return
     */
    @PutMapping
    public JsonResult manageShoppingCar(@RequestParam Long userId,@RequestParam String articleNumbers) {
        omsShoppingCartInfoService.manageShoppingCar(userId,articleNumbers);
        return success();
    }

    /**
     * @description 清空购物车
     * @param userId
     * @return
     */
    @DeleteMapping
    public JsonResult clearShoppingCar(@RequestParam Long userId) {
        omsShoppingCartInfoService.clearShoppingCar(userId);
        return success();
    }

}
