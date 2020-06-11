package com.liyuan.wave.ums.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liyuan.wave.po.entity.ums.UmsAdminEntity;
import com.liyuan.wave.ums.mapper.UmsAdminDao;
import com.liyuan.wave.ums.service.UmsAdminService;
import org.springframework.stereotype.Service;

/**
 * @description
 *
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-11 23:17:46
 */
@Service("umsAdminService")
public class UmsAdminServiceImpl extends ServiceImpl<UmsAdminDao, UmsAdminEntity> implements UmsAdminService {

}