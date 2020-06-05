package com.langchao.waveservicemall.service;


import com.langchao.wavecommon.vo.response.PageInfo;
import com.langchao.waveservicemall.pojo.dto.ColumnNatureDTO;
import com.langchao.waveservicemall.pojo.vo.ProductVo;

import java.util.List;

/**
 * @Title: ProductSkuNatureService
 * @ProjectName
 * @Description: TODO
 * @author 
 * @date Mon Feb 17 16:34:44 CST 2020
 */
public interface ProductSkuNatureService  {

    /**
     * @Author liyuan
     * @Description 根据产品id 列举产品属性信息
     * @param productId
     * @return
     */
    List<ColumnNatureDTO> selectProductNormByProductId(Integer productId);

   IPage findManagerList(Integer page, Integer size, Integer id);

    /**
     * @author 李源
     * @Description 计算商品库存
     * @param productVo
     * @return
     */
    Integer getStock(ProductVo productVo);

}
