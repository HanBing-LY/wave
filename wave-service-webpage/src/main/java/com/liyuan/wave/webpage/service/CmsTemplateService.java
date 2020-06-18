package com.liyuan.wave.webpage.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liyuan.wave.po.cms.CmsTemplate;
import com.liyuan.wave.webpage.mapper.CmsTemplateMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CmsTemplateService extends ServiceImpl<CmsTemplateMapper, CmsTemplate> {
    public List<CmsTemplate> findAllCmsTemplate(String siteId) {

        QueryWrapper<CmsTemplate> wrapper = new QueryWrapper<>();
        wrapper.eq("site_id",siteId);
        return this.list(wrapper);
    }
}
