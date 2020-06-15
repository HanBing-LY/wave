package com.liyuan.wave.ums.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liyuan.wave.po.entity.ums.UmsUserInfo;
import com.liyuan.wave.ums.Mapper.UmsUserInfoMapper;
import com.liyuan.wave.ums.service.UmsUserInfoService;
import org.springframework.stereotype.Service;

/**
 * @author liyuan
 * @description
 * @date 2020-06-15 15:46
 */
@Service
public class UmsUserInfoServiceImpl extends ServiceImpl<UmsUserInfoMapper, UmsUserInfo> implements UmsUserInfoService {
}
