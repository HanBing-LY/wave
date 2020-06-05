package com.langchao.waveservicemall.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.langchao.waveservicemall.pojo.OrderInfo;
import com.langchao.waveservicemall.pojo.dto.OrderInfoDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface OrderInfoMapper extends BaseMapper<OrderInfo> {

    //扣除用户积分
    int reduceUserScore(@Param("userId") Integer userId, @Param("reduceScore") Integer reduceScore);

    /**
     *@Description 后台list
     *@Author Renjinliang
     *@date 2020/3/26 13:28
     */
    List<OrderInfoDto> findManagerList(Map map);
}