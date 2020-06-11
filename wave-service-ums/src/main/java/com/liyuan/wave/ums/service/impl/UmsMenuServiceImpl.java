package com.liyuan.wave.ums.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liyuan.wave.po.entity.ums.UmsMenuEntity;
import com.liyuan.wave.ums.mapper.UmsMenuDao;
import com.liyuan.wave.ums.service.UmsMenuService;
import org.springframework.stereotype.Service;

/**
 * @description
 *
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-11 23:17:46
 */
@Service("umsMenuService")
public class UmsMenuServiceImpl extends ServiceImpl<UmsMenuDao, UmsMenuEntity> implements UmsMenuService {
}