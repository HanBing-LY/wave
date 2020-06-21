package com.liyuan.wave.oms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liyuan.wave.common.cache.constant.ShoppingCartCache;
import com.liyuan.wave.oms.mapper.OmsShoppingCartInfoMapper;
import com.liyuan.wave.oms.mapper.OmsShoppingCartItemMapper;
import com.liyuan.wave.oms.po.dto.OmsShoppingCartItemDto;
import com.liyuan.wave.oms.service.OmsShoppingCartInfoService;
import com.liyuan.wave.oms.service.OmsShoppingCartItemService;
import com.liyuan.wave.po.oms.OmsShoppingCartItem;
import com.liyuan.wave.po.oms.vo.OmsShoppingCartInfoVo;
import com.liyuan.wave.po.oms.vo.OmsShoppingCartItemSaveVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * @author liyuan
 * @description oms_shopping_cart_item
 * @email 724837404@qq.com
 * @date 2020-06-15 16:11:12
 */
@Service("omsShoppingCartItemService")
public class OmsShoppingCartItemServiceImpl extends ServiceImpl<OmsShoppingCartItemMapper, OmsShoppingCartItem> implements OmsShoppingCartItemService {

    @Autowired
    private OmsShoppingCartInfoMapper omsShoppingCartInfoMapper;
    @Autowired
    private OmsShoppingCartItemMapper omsShoppingCartItemMapper;
    @Autowired
    private OmsShoppingCartInfoService omsShoppingCartInfoService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public OmsShoppingCartInfoVo modifyCount(OmsShoppingCartItemSaveVo omsShoppingCartItemSaveVo) {
        String articleNumber = omsShoppingCartItemSaveVo.getArticleNumber();
        String shoppingCartNumber = omsShoppingCartItemSaveVo.getShoppingCartNumber();
        Long count = omsShoppingCartItemSaveVo.getCount();
        Long increment = stringRedisTemplate.opsForHash().increment(ShoppingCartCache.INFO + shoppingCartNumber, articleNumber, count);
        OmsShoppingCartItemDto omsShoppingCartItemDto = omsShoppingCartItemMapper.selectByShoppingCartNumberAndArticleNumber(shoppingCartNumber,articleNumber);
        BigDecimal productPrice = omsShoppingCartItemDto.getProductPrice();
        if(increment < 1L){
            stringRedisTemplate.opsForHash().delete(ShoppingCartCache.INFO + shoppingCartNumber, articleNumber);
            omsShoppingCartItemMapper.disableByArticleNumber(shoppingCartNumber,articleNumber);
        }else {
            omsShoppingCartItemMapper.updateByArticleNumber(shoppingCartNumber,articleNumber);
        }
        omsShoppingCartInfoMapper.modify(shoppingCartNumber,count,productPrice);
        return omsShoppingCartInfoService.showShoppingCar(shoppingCartNumber);
    }
}