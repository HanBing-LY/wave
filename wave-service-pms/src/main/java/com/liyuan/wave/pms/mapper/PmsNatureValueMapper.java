package com.liyuan.wave.pms.mapper;

import com.liyuan.wave.pms.po.dto.PmsNatureValueDto;
import com.liyuan.wave.po.pms.PmsNatureValue;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description pms_nature_value
 * 
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-15 16:10:20
 */
@Mapper
public interface PmsNatureValueMapper extends BaseMapper<PmsNatureValue> {

    /**
     * @description  查询属性通用值
     * @param id
     * @param start
     * @param end
     * @return
     */
    List<PmsNatureValueDto> queryByColumnNatureId(@Param("id") Long id, @Param("start") long start, @Param("end") long end);

}
