package com.liyuan.wave.oms.mapper;

import com.liyuan.wave.oms.po.dto.OmsOrderInfoDto;
import com.liyuan.wave.po.oms.OmsOrderInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @description oms_order_info
 * 
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-15 16:11:12
 */
@Mapper
public interface OmsOrderInfoMapper extends BaseMapper<OmsOrderInfo> {

    /**
     * @description 订单编号查询订单
     * @param orderNumber
     * @return
     */
    OmsOrderInfoDto selectByOrderNumber(@Param("orderNumber") String orderNumber);
}
