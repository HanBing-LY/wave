package com.liyuan.wave.wms.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liyuan.wave.wms.entity.WareSkuEntity;
import com.liyuan.wave.wms.mapper.WareSkuDao;
import com.liyuan.wave.wms.service.WareSkuService;
import org.springframework.stereotype.Service;

@Service("wareSkuService")
public class WareSkuServiceImpl extends ServiceImpl<WareSkuDao, WareSkuEntity> implements WareSkuService {


}