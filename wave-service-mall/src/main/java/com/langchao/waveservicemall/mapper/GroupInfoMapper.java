package com.langchao.waveservicemall.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.langchao.waveservicemall.pojo.GroupInfo;
import com.langchao.waveservicemall.pojo.dto.GroupInfoDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GroupInfoMapper extends BaseMapper<GroupInfo> {
    /**
     * @Author liyuan
     * @Description: 产品对应的拼团俱乐部list
     * @param productId
     * @return
     */
    List<GroupInfoDTO> listGetClubGroupByproductId(@Param("productId") Integer productId);

    /**
     * @Author liyuan
     * @Description: 查询指定的拼团组合(俱乐部某个拼团组合) (会出现不成功的)
     * @param  groupNumber
     * @return
     */
    List<GroupInfoDTO> groupClubByGroupNumber(@Param("groupNumber") Integer groupNumber);

    /**
     * @Author liyuan
     * @Description     1-0-1-5-1 联盟拼购-发起拼购成功-确认订单-支付成功-分享
     * @param groupNumber
     * @return
     */
    List<GroupInfoDTO> groupShare(@Param("groupNumber") Integer groupNumber, @Param("userId") Integer userId);

    /**
     * @Author liyuan
     * @Description      统计商品的拼团数量
     * @param productInfoId
     * @return
     */
    Integer sumGroupSaleNumberByProductId(Integer productInfoId);
}