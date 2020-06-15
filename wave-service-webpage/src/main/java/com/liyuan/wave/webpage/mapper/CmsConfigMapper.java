package com.liyuan.wave.webpage.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liyuan.wave.po.webpage.cms.CmsConfig;

public interface CmsConfigMapper extends BaseMapper<CmsConfig> {
    CmsConfig getCmsConfigAndModel(String id);
}
