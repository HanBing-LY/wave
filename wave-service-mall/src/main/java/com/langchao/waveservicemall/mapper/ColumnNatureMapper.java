package com.langchao.waveservicemall.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.langchao.waveservicemall.pojo.ColumnNature;
import com.langchao.waveservicemall.pojo.dto.ColumnNatureDTO;

import java.util.List;
import java.util.Map;

public interface ColumnNatureMapper extends BaseMapper<ColumnNature> {
    List<ColumnNatureDTO> findManagerList(Map map);

    Integer findCountByColumnId(Integer columnId);
}