package com.liyuan.wave.pms.mapper;

import com.liyuan.wave.pms.po.dto.PmsColumnNatureDto;
import com.liyuan.wave.po.pms.PmsColumnNature;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description pms_column_nature
 * 
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-15 16:10:20
 */
@Mapper
public interface PmsColumnNatureMapper extends BaseMapper<PmsColumnNature> {

    /**
     * @description  查询分类通用属性
     * @param id
     * @param start
     * @param end
     * @return
     */
    List<PmsColumnNatureDto> queryByColumnId(@Param("id") Long id,@Param("start") long start,@Param("end") long end);
}
