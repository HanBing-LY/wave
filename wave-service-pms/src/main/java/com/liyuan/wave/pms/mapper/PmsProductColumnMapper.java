package com.liyuan.wave.pms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liyuan.wave.pms.po.dto.PmsProductColumnDto;
import com.liyuan.wave.po.pms.PmsProductColumn;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description pms_product_column
 * 
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-15 16:10:20
 */
@Mapper
public interface PmsProductColumnMapper extends BaseMapper<PmsProductColumn> {

    /**
     * @description 根据id查询自定义商品分类
     * @param id
     * @return
     */
    List<PmsProductColumnDto> getCommonDetailById(@Param("id") Long id);


    /**
     * @description 查询所有的X级分类,逐级递减
     * @param parentId
     * @return
     */
    List<PmsProductColumnDto> getNextChildrenByParentId(@Param ("parentId") Long parentId);
}
