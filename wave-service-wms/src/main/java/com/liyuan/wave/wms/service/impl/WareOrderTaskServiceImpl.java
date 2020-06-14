package com.liyuan.wave.wms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liyuan.wave.po.entity.wms.WareOrderTaskEntity;
import com.liyuan.wave.wms.mapper.WareOrderTaskDao;
import com.liyuan.wave.wms.service.WareOrderTaskService;
import org.springframework.stereotype.Service;


@Service("wareOrderTaskService")
public class WareOrderTaskServiceImpl extends ServiceImpl<WareOrderTaskDao, WareOrderTaskEntity> implements WareOrderTaskService {

}