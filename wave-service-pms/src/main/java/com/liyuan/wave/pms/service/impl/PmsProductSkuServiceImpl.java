package com.liyuan.wave.pms.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.liyuan.wave.pms.mapper.PmsProductSkuMapper;
import com.liyuan.wave.po.pms.PmsProductSku;
import com.liyuan.wave.pms.service.PmsProductSkuService;

/**
 * @description pms_product_sku
 *
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-15 16:10:20
 */
@Service("pmsProductSkuService")
public class PmsProductSkuServiceImpl extends ServiceImpl<PmsProductSkuMapper, PmsProductSku> implements PmsProductSkuService {

}