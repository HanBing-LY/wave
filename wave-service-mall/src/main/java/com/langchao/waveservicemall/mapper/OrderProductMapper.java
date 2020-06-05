package com.langchao.waveservicemall.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.langchao.waveservicemall.pojo.OrderProduct;
import com.langchao.waveservicemall.pojo.UserAddress;
import com.langchao.waveservicemall.pojo.dto.OrderProductDto;
import com.langchao.waveservicemall.pojo.vo.OrderDetailVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderProductMapper extends BaseMapper<OrderProduct> {

    /**
     * @Author liyuan
     * @Description 根据订单编号加载订单详情
     * @param orderNumber
     * @return
     */
    List<OrderDetailVo> getOrderDetailByOrderNumber(String orderNumber);

    /**
     * @Author liyuan
     * @Description 选择收货地址
     * @param orderNumber
     * @param userAddress
     */
    void chooseReceiveAddress(@Param("orderNumber") String orderNumber, @Param("userAddress") UserAddress userAddress);

    /**
     *@Description 后台
     *@Author Renjinliang
     *@date 2020/3/26 17:23
     */
    List<OrderProductDto> findManagerList(@Param("id") Integer id);
}