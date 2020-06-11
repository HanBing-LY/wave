package com.liyuan.wave.pms.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liyuan.wave.pms.mapper.PmsCategoryDao;
import com.liyuan.wave.pms.service.PmsCategoryService;
import com.liyuan.wave.po.entity.PmsCategoryEntity;
import org.springframework.stereotype.Service;

/**
 * @description
 *
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-11 23:49:58
 */
@Service("pmsCategoryService")
public class PmsCategoryServiceImpl extends ServiceImpl<PmsCategoryDao, PmsCategoryEntity> implements PmsCategoryService {
}