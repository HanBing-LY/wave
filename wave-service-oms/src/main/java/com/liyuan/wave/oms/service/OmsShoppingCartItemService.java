package com.liyuan.wave.oms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liyuan.wave.po.oms.OmsShoppingCartItem;
import com.liyuan.wave.po.oms.vo.OmsShoppingCartInfoVo;
import com.liyuan.wave.po.oms.vo.OmsShoppingCartItemSaveVo;

/**
 * @description oms_shopping_cart_item
 *
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-15 16:11:12
 */
public interface OmsShoppingCartItemService extends IService<OmsShoppingCartItem> {

    /**
     * @description 修改购物车中某个商品
     * @param omsShoppingCartItemSaveVo
     * @return
     */
    OmsShoppingCartInfoVo modifyCount(OmsShoppingCartItemSaveVo omsShoppingCartItemSaveVo);
}

