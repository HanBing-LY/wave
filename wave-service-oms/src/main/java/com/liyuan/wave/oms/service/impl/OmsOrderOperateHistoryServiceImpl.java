package com.liyuan.wave.oms.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liyuan.wave.oms.mapper.OmsOrderOperateHistoryDao;
import com.liyuan.wave.oms.service.OmsOrderOperateHistoryService;
import com.liyuan.wave.po.entity.oms.OmsOrderOperateHistoryEntity;
import org.springframework.stereotype.Service;

/**
 * @description
 *
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-11 23:17:46
 */
@Service("omsOrderOperateHistoryService")
public class OmsOrderOperateHistoryServiceImpl extends ServiceImpl<OmsOrderOperateHistoryDao, OmsOrderOperateHistoryEntity> implements OmsOrderOperateHistoryService {

}