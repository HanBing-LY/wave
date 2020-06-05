package com.langchao.waveservicemall.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.langchao.wavecommon.util.StringUtils;
import com.langchao.waveservicemall.constant.PageControlInformations;
import com.langchao.waveservicemall.mapper.GroupInfoDetailMapper;
import com.langchao.waveservicemall.mapper.GroupInfoMapper;
import com.langchao.waveservicemall.pojo.GroupInfoDetail;
import com.langchao.waveservicemall.pojo.dto.GroupInfoDTO;
import com.langchao.waveservicemall.service.GroupInfoDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Title: GroupInfoDetailServiceImpl
 * @ProjectName
 * @Description: TODO
 * @author 
 * @date Mon Feb 17 16:34:43 CST 2020
 */
@Service
@Transactional
public class GroupInfoDetailServiceImpl extends ServiceImpl<GroupInfoDetailMapper, GroupInfoDetail> implements GroupInfoDetailService {
    @Autowired
    private GroupInfoDetailMapper groupInfoDetailMapper;
    @Autowired
    private GroupInfoMapper groupInfoMapper;

    /**
     * @Author liyuan
     * @Description 根据产品id 分页查询拼购俱乐部
     * @param productId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public IPage listGroupClubByPage(Integer productId, Integer pageNum, Integer pageSize) {
        //        查询所有 正在拼团的俱乐部(默认只获取前两条数据)
        if(StringUtils.isNull(pageNum) || pageNum < 1){
            pageNum = PageControlInformations.DEFAULT_PAGE_NUMBER;
        }
        if(StringUtils.isNull(pageSize) || pageSize < 1 || pageSize > PageControlInformations.MAX_PAGE_SIZE){
            pageSize = PageControlInformations.CLUB_GROUP_SALE_DEFAULT_PAGE_SIZE;
        }
        return groupInfoDetailMapper.selectPage(new Page<GroupInfoDetail>(pageNum,pageSize),new QueryWrapper<GroupInfoDetail>());
    }

    /**
     * @Author liyuan
     * @Description 1-0-1-1-5 俱乐部拼购-商品详情-加入拼购 根据groupNumber拼团编码查询拼购信息
     * @param groupNumber
     * @return
     */
    @Override
    public GroupInfoDTO groupClubByGroupNumber(Integer groupNumber) {
//        唯一查询
        List<GroupInfoDTO> groupInfoDTOS = groupInfoMapper.groupClubByGroupNumber(groupNumber);
        if(groupInfoDTOS.size() != 1){
//            日志:数据库信息错误 productId clubId
        }
//        计算商品的拼团数量
        GroupInfoDTO groupInfoDTO = groupInfoDTOS.get(0);
        Integer productGroupTotalPeople = groupInfoMapper.sumGroupSaleNumberByProductId(groupInfoDTO.getProductInfoId());
        groupInfoDTO.setProductGroupTotalPeople(productGroupTotalPeople);
        groupInfoDTO.setSecondToEndTime(groupInfoDTO.getEndTime().getTime() - System.currentTimeMillis());
        return groupInfoDTO;
    }

    /**
     * @Author liyuan
     * @Description     1-0-1-5-1 联盟拼购-发起拼购成功-确认订单-支付成功-分享
     * @param groupNumber
     * @return
     */
    @Override
    public GroupInfoDTO groupShare(Integer groupNumber,Integer userId) {
        //        唯一查询
        List<GroupInfoDTO> groupInfoDTOS = groupInfoMapper.groupShare(groupNumber,userId);
        if(groupInfoDTOS.size() != 1){
//            日志:数据库信息错误 productId clubId
        }
        return groupInfoDTOS.get(0);
    }

    /**
     * @Author liyuan
     * @Description     1-0-1-5-0 联盟拼购-我的拼团
     * @param userId
     * @return
     */
    @Override
    public List<GroupInfoDTO> myAll(Integer userId) {
        return groupInfoMapper.groupShare(null,userId);
    }
}
