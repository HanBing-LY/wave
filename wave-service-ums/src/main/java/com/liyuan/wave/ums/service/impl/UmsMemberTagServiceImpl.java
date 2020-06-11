package com.liyuan.wave.ums.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liyuan.wave.po.entity.ums.UmsMemberTagEntity;
import com.liyuan.wave.ums.mapper.UmsMemberTagDao;
import com.liyuan.wave.ums.service.UmsMemberTagService;
import org.springframework.stereotype.Service;

/**
 * @description
 *
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-11 23:17:47
 */
@Service("umsMemberTagService")
public class UmsMemberTagServiceImpl extends ServiceImpl<UmsMemberTagDao, UmsMemberTagEntity> implements UmsMemberTagService {
}