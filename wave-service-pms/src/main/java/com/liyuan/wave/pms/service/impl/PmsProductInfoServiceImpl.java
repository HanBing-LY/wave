package com.liyuan.wave.pms.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.liyuan.wave.pms.mapper.PmsProductInfoMapper;
import com.liyuan.wave.po.pms.PmsProductInfo;
import com.liyuan.wave.pms.service.PmsProductInfoService;

/**
 * @description pms_product_info
 *
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-15 16:10:20
 */
@Service("pmsProductInfoService")
public class PmsProductInfoServiceImpl extends ServiceImpl<PmsProductInfoMapper, PmsProductInfo> implements PmsProductInfoService {

}