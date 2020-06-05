package com.langchao.waveservicemall.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.langchao.waveservicemall.pojo.GroupInfoDetail;
import com.langchao.waveservicemall.pojo.dto.GroupInfoDTO;

import java.util.List;

/**
 * @Title: GroupInfoDetailService
 * @ProjectName
 * @Description: TODO
 * @author 
 * @date Mon Feb 17 16:34:43 CST 2020
 */
public interface GroupInfoDetailService  extends IService<GroupInfoDetail> {

    /**
     * @Author liyuan
     * @Description 根据产品id 分页查询拼购俱乐部
     * @param productId
     * @param pageNum
     * @param pageSize
     * @return
     */
   IPage listGroupClubByPage(Integer productId, Integer pageNum, Integer pageSize);

    /**
     * @Author liyuan
     * @Description 1-0-1-1-5 俱乐部拼购-商品详情-加入拼购 根据groupNumber拼团编码查询拼购信息
     * @param groupNumber
     * @return
     */
    GroupInfoDTO groupClubByGroupNumber(Integer groupNumber);

    /**
     * @Author liyuan
     * @Description     1-0-1-5-1 联盟拼购-发起拼购成功-确认订单-支付成功-分享
     * @param groupNumber
     * @return
     */
    GroupInfoDTO groupShare(Integer groupNumber, Integer userId);

    /**
     * @Author liyuan
     * @Description     1-0-1-5-0 联盟拼购-我的拼团
     * @param userId
     * @return
     */
    List<GroupInfoDTO> myAll(Integer userId);
}
