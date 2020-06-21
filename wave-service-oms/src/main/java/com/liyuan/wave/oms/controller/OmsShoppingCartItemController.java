package com.liyuan.wave.oms.controller;

import com.liyuan.wave.common.vo.response.JsonResult;
import com.liyuan.wave.common.web.BaseController;
import com.liyuan.wave.oms.service.OmsShoppingCartItemService;
import com.liyuan.wave.po.oms.vo.OmsShoppingCartItemSaveVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @description oms_shopping_cart_item
 *
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-15 16:11:12
 */
@RestController
@RequestMapping("oms/omsshoppingcartitem")
public class OmsShoppingCartItemController extends BaseController {

    @Autowired
    private OmsShoppingCartItemService omsShoppingCartItemService;

    /**
     * @description 修改购物车中某个商品
     * @param omsShoppingCartItemSaveVo
     * @return
     */
    @PutMapping
    public JsonResult modifyCount(@RequestBody OmsShoppingCartItemSaveVo omsShoppingCartItemSaveVo){
        return success(omsShoppingCartItemService.modifyCount(omsShoppingCartItemSaveVo));
    }

}
