package com.liyuan.wave.ums.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liyuan.wave.po.entity.ums.UmsMemberTaskEntity;
import com.liyuan.wave.ums.mapper.UmsMemberTaskDao;
import com.liyuan.wave.ums.service.UmsMemberTaskService;
import org.springframework.stereotype.Service;

/**
 * @description
 *
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-11 23:17:47
 */
@Service("umsMemberTaskService")
public class UmsMemberTaskServiceImpl extends ServiceImpl<UmsMemberTaskDao, UmsMemberTaskEntity> implements UmsMemberTaskService {
}