package com.liyuan.wave.oms.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.liyuan.wave.oms.mapper.OmsOrderProductMapper;
import com.liyuan.wave.po.oms.OmsOrderProduct;
import com.liyuan.wave.oms.service.OmsOrderProductService;

/**
 * @description oms_order_product
 *
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-15 16:11:12
 */
@Service("omsOrderProductService")
public class OmsOrderProductServiceImpl extends ServiceImpl<OmsOrderProductMapper, OmsOrderProduct> implements OmsOrderProductService {

}