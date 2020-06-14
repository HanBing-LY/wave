package com.liyuan.wave.wms.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liyuan.wave.po.entity.wms.WareOrderTaskDetailEntity;
import com.liyuan.wave.wms.mapper.WareOrderTaskDetailDao;
import com.liyuan.wave.wms.service.WareOrderTaskDetailService;
import org.springframework.stereotype.Service;

@Service("wareOrderTaskDetailService")
public class WareOrderTaskDetailServiceImpl extends ServiceImpl<WareOrderTaskDetailDao, WareOrderTaskDetailEntity> implements WareOrderTaskDetailService {

}