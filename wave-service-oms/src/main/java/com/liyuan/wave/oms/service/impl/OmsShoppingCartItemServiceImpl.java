package com.liyuan.wave.oms.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.liyuan.wave.oms.dao.OmsShoppingCartItemDao;
import com.liyuan.wave.po.entity.oms.OmsShoppingCartItemEntity;
import com.liyuan.wave.oms.service.OmsShoppingCartItemService;

/**
 * @description oms_shopping_cart_item
 *
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-15 16:11:12
 */
@Service("omsShoppingCartItemService")
public class OmsShoppingCartItemServiceImpl extends ServiceImpl<OmsShoppingCartItemDao, OmsShoppingCartItemEntity> implements OmsShoppingCartItemService {

}