package com.langchao.waveservicemall.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.langchao.waveservicemall.pojo.FlashSaleProductSku;
import com.langchao.waveservicemall.pojo.vo.FlashSaleProductSkuVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FlashSaleProductSkuMapper extends BaseMapper<FlashSaleProductSku> {

    /**
     * @author liyuan
     * @Description 删除商品秒杀
     * @param flashSaleProductIds
     */
    void deleteByProductSkuIds(@Param("flashSaleProductIds") List<Integer> flashSaleProductIds);

    /**
     * @description 获取秒杀商品的库存
     * @param
     * @return
     **/
    Integer getFlashSaleProduckStock(@Param("productId") Integer productId);

    /**
     * @author liyuan
     * @Description 分页查询控制器
     * @param flashSaleProductId
     * @return
     */
    List<FlashSaleProductSkuVo> list(@Param("flashSaleProductId") Integer flashSaleProductId);

    /**
     * @author liyuan
     * @Description 根据flashsaleid 查询
     * @param flashSaleIds
     * @return
     */
    List<FlashSaleProductSku> selectByFlashSaleIds(@Param("flashSaleIds") List<Integer> flashSaleIds);
}