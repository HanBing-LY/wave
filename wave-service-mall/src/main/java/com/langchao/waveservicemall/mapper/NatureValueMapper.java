package com.langchao.waveservicemall.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.langchao.waveservicemall.pojo.NatureValue;

import java.util.List;
import java.util.Map;

/**
 * @Title: NatureValueMapper
 * @ProjectName
 * @Description: TODO
 * @author 
 * @date Mon Feb 17 16:34:44 CST 2020
 */

public interface NatureValueMapper extends BaseMapper<NatureValue> {

    List<NatureValue> findManagerList(Map map);
}

