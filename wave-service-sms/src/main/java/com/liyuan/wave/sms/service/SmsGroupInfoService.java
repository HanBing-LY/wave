package com.liyuan.wave.sms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liyuan.wave.po.sms.SmsGroupInfo;
import com.liyuan.wave.sms.po.vo.SmsGroupInfoDetailVo;
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
     * @param articleNumber
     * @param flag 是否加载所有
     * @return
     */
    List<SmsGroupInfoVo> listGroupClubByPage(String articleNumber,boolean flag);

    /**
     * @description   根据groupNumber拼团编码查询拼购信息
     * @param groupNumber 团唯一标识
     * @return
     */
    SmsGroupInfoDetailVo groupClubByGroupNumber(String groupNumber);

    /**
     * @description  拼团加入,生成订单
     * @param groupNumber
     */
    void joinGroup(String groupNumber);

    /**
     * @description  建团
     * @param articleNumber 商品编码
     */
    void createGroup(String articleNumber,Long groupSaleProductId);
}

