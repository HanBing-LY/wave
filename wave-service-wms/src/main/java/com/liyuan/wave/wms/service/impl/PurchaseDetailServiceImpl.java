package com.liyuan.wave.wms.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liyuan.wave.wms.entity.PurchaseDetailEntity;
import com.liyuan.wave.wms.mapper.PurchaseDetailDao;
import com.liyuan.wave.wms.service.PurchaseDetailService;
import org.springframework.stereotype.Service;

@Service("purchaseDetailService")
public class PurchaseDetailServiceImpl extends ServiceImpl<PurchaseDetailDao, PurchaseDetailEntity> implements PurchaseDetailService {
}