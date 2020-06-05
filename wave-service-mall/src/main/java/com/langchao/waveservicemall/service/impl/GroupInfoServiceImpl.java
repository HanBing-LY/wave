package com.langchao.waveservicemall.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.langchao.waveservicemall.mapper.GroupInfoMapper;
import com.langchao.waveservicemall.pojo.GroupInfo;
import com.langchao.waveservicemall.service.GroupInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Title: GroupInfoServiceImpl
 * @ProjectName
 * @Description: TODO
 * @author 
 * @date Mon Feb 17 16:34:43 CST 2020
 */
@Service
public class GroupInfoServiceImpl extends ServiceImpl<GroupInfoMapper,GroupInfo> implements GroupInfoService {
    @Autowired
    private GroupInfoMapper groupInfoMapper;

}
