package com.liyuan.wave.wms.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liyuan.wave.wms.entity.WareInfoEntity;
import com.liyuan.wave.wms.mapper.WareInfoDao;
import com.liyuan.wave.wms.service.WareInfoService;
import org.springframework.stereotype.Service;

@Service("wareInfoService")
public class WareInfoServiceImpl extends ServiceImpl<WareInfoDao, WareInfoEntity> implements WareInfoService {


}