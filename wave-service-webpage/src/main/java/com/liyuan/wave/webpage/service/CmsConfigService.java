package com.liyuan.wave.webpage.service;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liyuan.wave.po.webpage.cms.CmsConfig;
import com.liyuan.wave.webpage.mapper.CmsConfigMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CmsConfigService extends ServiceImpl<CmsConfigMapper, CmsConfig> {

    @Resource
    private CmsConfigMapper cmsConfigMapper;

    public CmsConfig getCmsConfigAndModel(String id) {
        return cmsConfigMapper.getCmsConfigAndModel(id);
    }
}
