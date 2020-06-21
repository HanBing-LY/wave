package com.liyuan.wave.oms.mapper;

import com.liyuan.wave.oms.po.dto.OmsShoppingCartItemDto;
import com.liyuan.wave.po.oms.OmsShoppingCartItem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description oms_shopping_cart_item
 * 
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-15 16:11:12
 */
@Mapper
public interface OmsShoppingCartItemMapper extends BaseMapper<OmsShoppingCartItem> {

    /**
     * @description 清空购物车数据
     * @param shoppingCartNumber
     */
    void clearShoppingCarItem(@Param("shoppingCartNumber") String shoppingCartNumber);

    /**
     * @description 查询各系列商品的信息
     * @param shoppingCartNumber
     * @return
     */
    List<OmsShoppingCartItemDto> selectByShoppingCartNumber(@Param("shoppingCartNumber") String shoppingCartNumber);

    /**
     * @description 修改数量
     * @param articleNumber
     */
    void updateByArticleNumber(@Param("shoppingCartNumber") String shoppingCartNumber,@Param("articleNumber") String articleNumber);

    /**
     * @description 移除某个商品数据
     * @param shoppingCartNumber
     * @param articleNumber
     */
    void disableByArticleNumber(@Param("shoppingCartNumber") String shoppingCartNumber,@Param("articleNumber") String articleNumber);

    /**
     * @description 查询系列商品的信息
     * @param shoppingCartNumber
     * @param articleNumber
     * @return
     */
    OmsShoppingCartItemDto selectByShoppingCartNumberAndArticleNumber(@Param("shoppingCartNumber") String shoppingCartNumber,@Param("articleNumber") String articleNumber);
}
