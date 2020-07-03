package com.liyuan.wave.oms.mapper;

import com.liyuan.wave.oms.po.dto.OmsOrderProductDto;
import com.liyuan.wave.po.oms.OmsOrderProduct;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description oms_order_product
 * 
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-15 16:11:12
 */
@Mapper
public interface OmsOrderProductMapper extends BaseMapper<OmsOrderProduct> {

    /**
     * @description 订单编号查询订单详情
     * @param orderNumber
     * @return
     */
    List<OmsOrderProductDto> selectByOrderNumber(@Param("orderNumber") String orderNumber);
}
