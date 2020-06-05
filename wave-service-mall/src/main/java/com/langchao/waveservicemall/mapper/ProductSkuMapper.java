package com.langchao.waveservicemall.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.langchao.waveservicemall.pojo.ProductSku;
import com.langchao.waveservicemall.pojo.dto.ProductSkuDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductSkuMapper extends BaseMapper<ProductSku> {

    /**
     * @param productSkuId
     * @param productNum
     * @Author liyuan
     * @Description 减库存
     */
    void updateStock(@Param("productSkuId") Integer productSkuId, @Param("productNum") Integer productNum);

    /**
     * @Description 根据产品id差总数
     * @Author Renjinliang
     * @date 2020/3/24 14:20
     */
    Integer countByproductId(Integer productId);

    /**
     * @param productId 产品id
     * @param stock     库存
     * @Description 根据产品id加库存
     * @Author Renjinliang
     * @date 2020/3/24 14:40
     */
    void updateByStock(@Param("productId") Integer productId, @Param("stock") Integer stock);

    /**
     * @param productId 产品id
     * @Description 后台list
     * @Author Renjinliang
     * @date 2020/3/24 16:26
     */
    List<ProductSkuDto> findManagerList(@Param("productId") Integer productId);

    void updateById(@Param("id") Integer id, @Param("stock") Integer stock);

    /**
     *@Description 根据产品id查询数据
     *@Author Renjinliang
     *@date 2020/3/25 13:50
     */
    List<ProductSkuDto> findByProductId(Integer productId);
}