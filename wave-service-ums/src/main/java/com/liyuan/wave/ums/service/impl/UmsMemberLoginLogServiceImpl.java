package com.liyuan.wave.ums.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liyuan.wave.po.entity.ums.UmsMemberLoginLogEntity;
import com.liyuan.wave.ums.mapper.UmsMemberLoginLogDao;
import com.liyuan.wave.ums.service.UmsMemberLoginLogService;
import org.springframework.stereotype.Service;

/**
 * @description
 *
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-11 23:17:46
 */
@Service("umsMemberLoginLogService")
public class UmsMemberLoginLogServiceImpl extends ServiceImpl<UmsMemberLoginLogDao, UmsMemberLoginLogEntity> implements UmsMemberLoginLogService {
}