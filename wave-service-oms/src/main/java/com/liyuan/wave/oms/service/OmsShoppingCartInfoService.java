package com.liyuan.wave.oms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liyuan.wave.po.oms.OmsShoppingCartInfo;
import com.liyuan.wave.po.oms.vo.OmsShoppingCartInfoVo;

/**
 * @description oms_shopping_cart_info
 *
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-15 16:11:12
 */
public interface OmsShoppingCartInfoService extends IService<OmsShoppingCartInfo> {

    /**
     * @description 展示购物车
     * @param shoppingCartNumber
     * @return
     */
    OmsShoppingCartInfoVo showShoppingCar(String shoppingCartNumber);

    /**
     * @description 移除某个购物车商品
     * @param userId
     * @param articleNumbers
     * @return
     */
    void manageShoppingCar(Long userId,String articleNumbers);

    /**
     * @description 清空购物车
     * @param userId
     * @return
     */
    void clearShoppingCar(Long userId);
}

