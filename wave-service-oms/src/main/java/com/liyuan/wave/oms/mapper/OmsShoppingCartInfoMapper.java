package com.liyuan.wave.oms.mapper;

import com.liyuan.wave.oms.po.dto.OmsShoppingCartInfoDto;
import com.liyuan.wave.po.oms.OmsShoppingCartInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

/**
 * @description oms_shopping_cart_info
 * 
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-15 16:11:12
 */
@Mapper
public interface OmsShoppingCartInfoMapper extends BaseMapper<OmsShoppingCartInfo> {

    /**
     * @description 清空购物车数据
     * @param shoppingCartNumber
     */
    void clearShoppingCar(@Param("shoppingCartNumber") String shoppingCartNumber);

    /**
     * @description 查购物车信息
     * @param shoppingCartNumber
     * @return
     */
    OmsShoppingCartInfoDto selectByShoppingCartNumber(@Param("shoppingCartNumber") String shoppingCartNumber);

    /**
     * @description 修改购物车数据
     * @param count
     * @param productPrice
     * @param shoppingCartNumber
     */
    void modify(@Param("shoppingCartNumber") String shoppingCartNumber,@Param("count")  Long count,@Param("productPrice")  BigDecimal productPrice);
}
