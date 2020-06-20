package com.liyuan.wave.sms.service.impl;

import com.liyuan.wave.sms.po.vo.SmsGroupInfoVo;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.liyuan.wave.sms.mapper.SmsGroupInfoMapper;
import com.liyuan.wave.po.sms.SmsGroupInfo;
import com.liyuan.wave.sms.service.SmsGroupInfoService;

import java.util.List;

/**
 * @description sms_group_info
 *
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-15 16:09:35
 */
@Service("smsGroupInfoService")
public class SmsGroupInfoServiceImpl extends ServiceImpl<SmsGroupInfoMapper, SmsGroupInfo> implements SmsGroupInfoService {

    @Override
    public List<SmsGroupInfoVo> listGroupClubByPage(Long productSkuId, Integer pageNum, Integer pageSize) {
        return null;
    }

    @Override
    public SmsGroupInfoVo groupClubByGroupNumber(Integer groupNumber) {
        return null;
    }
}