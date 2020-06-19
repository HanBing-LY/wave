package com.liyuan.wave.pms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liyuan.wave.pms.po.dto.PmsProductSkuDto;
import com.liyuan.wave.po.pms.PmsProductSku;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @description pms_product_sku
 * 
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-15 16:10:20
 */
@Mapper
public interface PmsProductSkuMapper extends BaseMapper<PmsProductSku> {

    /**
     * @description 根据货号查商品
     * @param articleNumber 货号
     * @return
     */
    PmsProductSkuDto selectByArticleNumber(@Param("articleNumber") String articleNumber);
}
