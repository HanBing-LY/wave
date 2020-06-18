package com.liyuan.wave.pms.mapper;

import com.liyuan.wave.pms.po.dto.PmsProductInfoDto;
import com.liyuan.wave.po.pms.PmsProductInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @description pms_product_info
 * 
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-15 16:10:20
 */
@Mapper
public interface PmsProductInfoMapper extends BaseMapper<PmsProductInfo> {

    /**
     * @description 查询五级分类下的所有商品
     * @param id
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<PmsProductInfoDto> listGetAllProductsByMinColumn(Long id,Integer pageNum, Integer pageSize);
}
