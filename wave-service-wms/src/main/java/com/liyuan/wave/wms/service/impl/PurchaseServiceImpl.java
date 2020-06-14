package com.liyuan.wave.wms.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liyuan.wave.po.entity.wms.PurchaseEntity;
import com.liyuan.wave.wms.mapper.PurchaseDao;
import com.liyuan.wave.wms.service.PurchaseService;
import org.springframework.stereotype.Service;

@Service("purchaseService")
public class PurchaseServiceImpl extends ServiceImpl<PurchaseDao, PurchaseEntity> implements PurchaseService {


}