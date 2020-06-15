package com.liyuan.wave.pms.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.liyuan.wave.pms.dao.PmsProductColumnDao;
import com.liyuan.wave.po.entity.pms.PmsProductColumnEntity;
import com.liyuan.wave.pms.service.PmsProductColumnService;

/**
 * @description pms_product_column
 *
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-15 16:10:20
 */
@Service("pmsProductColumnService")
public class PmsProductColumnServiceImpl extends ServiceImpl<PmsProductColumnDao, PmsProductColumnEntity> implements PmsProductColumnService {

}