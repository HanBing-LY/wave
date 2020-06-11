package com.liyuan.wave.ums.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liyuan.wave.po.entity.ums.UmsMemberEntity;
import com.liyuan.wave.ums.mapper.UmsMemberDao;
import com.liyuan.wave.ums.service.UmsMemberService;
import org.springframework.stereotype.Service;

/**
 * @description
 *
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-11 23:17:47
 */
@Service("umsMemberService")
public class UmsMemberServiceImpl extends ServiceImpl<UmsMemberDao, UmsMemberEntity> implements UmsMemberService {
}