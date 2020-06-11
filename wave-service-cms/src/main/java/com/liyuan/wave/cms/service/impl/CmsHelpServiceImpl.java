package com.liyuan.wave.cms.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liyuan.wave.cms.mapper.CmsHelpDao;
import com.liyuan.wave.cms.service.CmsHelpService;
import com.liyuan.wave.po.entity.cms.CmsHelpEntity;
import org.springframework.stereotype.Service;

/**
 * @description
 *
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-11 23:17:44
 */
@Service("cmsHelpService")
public class CmsHelpServiceImpl extends ServiceImpl<CmsHelpDao, CmsHelpEntity> implements CmsHelpService {
}