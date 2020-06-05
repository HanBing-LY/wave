package com.langchao.waveservicemall.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.langchao.waveservicemall.pojo.ProductSku;
import com.langchao.waveservicemall.pojo.ProductSkuNature;
import com.langchao.waveservicemall.pojo.dto.ColumnNatureDTO;
import com.langchao.waveservicemall.pojo.dto.ProductSkuNatureDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ProductSkuNatureMapper extends BaseMapper<ProductSkuNature> {

    List<ColumnNatureDTO> selectProductNormByProductId(Integer productId);

    /**
     * @Author liytuan
     * @Description 根据商品id,natureid,naturevalueid查productskuid
     * @param productId
     * @param natureIds
     * @param natureValueIds
     * @return
     */
    List<ProductSku> selectIdByProductIdAndNatureIdAndNatureValueId(@Param("productId") Integer productId, @Param("natureIds") List<Integer> natureIds, @Param("natureValueIds") List<Integer> natureValueIds);

    List<ProductSkuNatureDTO> findManagerList(Map map);

    /**
     *@Description 根据分类属性值id查询总条数
     *@Author Renjinliang
     *@date 2020/3/25 11:28
     */
    Integer CountByValueId(@Param("productSkuId") Integer productSkuId, @Param("columnNatureId") Integer columnNatureId, @Param("natureValueId") Integer natureValueId);

    /**
     *@Description 根据产品sku id 查询数据
     *@Author Renjinliang
     *@date 2020/3/25 13:54
     */
    List<ProductSkuNatureDTO> findByProductSkuId(@Param("productSkuId") Integer productSkuId);

    /**
     *@Description 根据分类属性id和属性值id查询
     *@Author Renjinliang
     *@date 2020/3/25 22:05
     */
    List<ProductSkuNature> findByIds(Map map);

    /**
     * @Author liyuan
     * @Description 分页查询秒杀控制
     * @param productId
     * @return
     */
    List<ColumnNatureDTO> listGetNatureToChoose(@Param("productId") Integer productId);

    /**
     *@Description 根据产品sku id 查询数据
     *@Author Renjinliang
     *@date 2020/3/25 13:54
     */
    List<ProductSkuNature> findByProductSkuIdList(@Param("productSkuIds") List<Integer> productSkuIds);
}