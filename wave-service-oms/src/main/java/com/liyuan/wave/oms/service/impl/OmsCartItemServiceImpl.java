package com.liyuan.wave.oms.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liyuan.wave.oms.mapper.OmsCartItemDao;
import com.liyuan.wave.oms.service.OmsCartItemService;
import com.liyuan.wave.po.entity.oms.OmsCartItemEntity;
import org.springframework.stereotype.Service;

/**
 * @description
 *
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-11 23:17:44
 */
@Service("omsCartItemService")
public class OmsCartItemServiceImpl extends ServiceImpl<OmsCartItemDao, OmsCartItemEntity> implements OmsCartItemService {
}