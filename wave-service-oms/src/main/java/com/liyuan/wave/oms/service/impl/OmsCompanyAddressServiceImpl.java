package com.liyuan.wave.oms.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.liyuan.wave.oms.dao.OmsCompanyAddressDao;
import com.liyuan.wave.po.entity.oms.OmsCompanyAddressEntity;
import com.liyuan.wave.oms.service.OmsCompanyAddressService;

/**
 * @description oms_company_address
 *
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-15 16:11:12
 */
@Service("omsCompanyAddressService")
public class OmsCompanyAddressServiceImpl extends ServiceImpl<OmsCompanyAddressDao, OmsCompanyAddressEntity> implements OmsCompanyAddressService {

}