package com.liyuan.wave.oms.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liyuan.wave.common.cache.constant.ShoppingCartCache;
import com.liyuan.wave.common.vo.response.JsonResult;
import com.liyuan.wave.oms.client.PmsProductSkuClient;
import com.liyuan.wave.oms.mapper.OmsShoppingCartInfoMapper;
import com.liyuan.wave.oms.mapper.OmsShoppingCartItemMapper;
import com.liyuan.wave.oms.po.dto.OmsShoppingCartInfoDto;
import com.liyuan.wave.oms.po.dto.OmsShoppingCartItemDto;
import com.liyuan.wave.oms.service.OmsShoppingCartInfoService;
import com.liyuan.wave.po.oms.OmsShoppingCartInfo;
import com.liyuan.wave.po.oms.vo.OmsShoppingCartInfoVo;
import com.liyuan.wave.po.oms.vo.OmsShoppingCartItemVo;
import com.liyuan.wave.po.pms.vo.PmsProductSkuVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author liyuan
 * @description oms_shopping_cart_info
 * @email 724837404@qq.com
 * @date 2020-06-15 16:11:12
 */
@Service("omsShoppingCartInfoService")
public class OmsShoppingCartInfoServiceImpl extends ServiceImpl<OmsShoppingCartInfoMapper, OmsShoppingCartInfo> implements OmsShoppingCartInfoService {

    @Autowired
    private PmsProductSkuClient pmsProductSkuClient;
    @Autowired
    private OmsShoppingCartInfoMapper omsShoppingCartInfoMapper;
    @Autowired
    private OmsShoppingCartItemMapper omsShoppingCartItemMapper;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public OmsShoppingCartInfoVo showShoppingCar(String shoppingCartNumber) {
        OmsShoppingCartInfoVo omsShoppingCartInfoVo = new OmsShoppingCartInfoVo();
        OmsShoppingCartInfoDto omsShoppingCartInfoDto  = omsShoppingCartInfoMapper.selectByShoppingCartNumber(shoppingCartNumber);
        List<OmsShoppingCartItemDto> omsShoppingCartItemDtoList = omsShoppingCartItemMapper.selectByShoppingCartNumber(shoppingCartNumber);
        omsShoppingCartInfoVo.setTotalCount(omsShoppingCartInfoDto.getTotalCount());
        omsShoppingCartInfoVo.setTotalPrice(omsShoppingCartInfoDto.getTotalPrice());
        List<OmsShoppingCartItemVo> collect = omsShoppingCartItemDtoList.stream().map(i -> {
            OmsShoppingCartItemVo omsShoppingCartItemVo = new OmsShoppingCartItemVo();
            BeanUtils.copyProperties(i, omsShoppingCartItemVo);
            JsonResult jsonResult = pmsProductSkuClient.getDetailByArticleNumber(i.getArticleNumber());
            Object jsonResultData = jsonResult.getData();
            PmsProductSkuVo pmsProductSkuVo = JSONObject.parseObject(jsonResultData.toString(), PmsProductSkuVo.class);
            omsShoppingCartItemVo.setArticleNumber(pmsProductSkuVo.getArticleNumber());
            omsShoppingCartItemVo.setProductIcon(pmsProductSkuVo.getProductIcon());
            omsShoppingCartItemVo.setProductOldPrice(pmsProductSkuVo.getProductOldPrice());
            omsShoppingCartItemVo.setProductSalePrice(pmsProductSkuVo.getProductSalePrice());
            omsShoppingCartItemVo.setProductDesc(pmsProductSkuVo.getProductDesc());
            omsShoppingCartItemVo.setType(pmsProductSkuVo.getType());
            return omsShoppingCartItemVo;
        }).collect(Collectors.toList());
        omsShoppingCartInfoVo.setOmsShoppingCartItemVoList(collect);
        return omsShoppingCartInfoVo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void clearShoppingCar(Long userId) {
        String shoppingCartNumber = "";
        omsShoppingCartInfoMapper.clearShoppingCar(shoppingCartNumber);
        omsShoppingCartItemMapper.clearShoppingCarItem(shoppingCartNumber);
        stringRedisTemplate.delete(ShoppingCartCache.INFO + shoppingCartNumber);
    }
}