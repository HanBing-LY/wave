package com.liyuan.wave.sms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liyuan.wave.po.sms.SmsGroupInfo;
import com.liyuan.wave.sms.po.vo.SmsGroupInfoVo;

import java.util.List;

/**
 * @description sms_group_info
 *
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-15 16:09:35
 */
public interface SmsGroupInfoService extends IService<SmsGroupInfo> {
    /**
     * @description  根据拼团商品查所有拼团
     * @param productSkuId
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<SmsGroupInfoVo> listGroupClubByPage(Long productSkuId, Integer pageNum, Integer pageSize);

    /**
     * @description   根据groupNumber拼团编码查询拼购信息
     * @param groupNumber
     * @return
     */
    SmsGroupInfoVo groupClubByGroupNumber(Integer groupNumber);
}

