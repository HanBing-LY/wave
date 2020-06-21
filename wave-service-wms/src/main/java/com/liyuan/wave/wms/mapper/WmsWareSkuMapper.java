package com.liyuan.wave.wms.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liyuan.wave.po.wms.WmsWareSku;
import com.liyuan.wave.wms.po.dto.WmsWareSkuDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author liyuan
 * @description
 * @date 2020-06-15 15:44
 */
@Mapper
public interface WmsWareSkuMapper extends BaseMapper<WmsWareSku> {

    /**
     * @description 根据商品货号查库存信息
     * @param articleNumber
     * @return
     */
    WmsWareSkuDto selectByArticleNumber(@Param("articleNumber") String articleNumber);

    /**
     * @description 扣减sku对应的库存
     * @param articleNumber
     * @param stockToDecrement
     * @return
     */
    Integer decrementStock(@Param("articleNumber") String articleNumber,@Param("stockToDecrement") Long stockToDecrement);
}
