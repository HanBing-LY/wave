package com.liyuan.wave.po.oms.vo.relation;

import com.liyuan.wave.po.oms.vo.OmsOrderInfoDetailVo;
import com.liyuan.wave.po.oms.vo.OmsOrderProductVo;
import lombok.Data;

import java.util.List;

/**
 * @author : liyuan  
 * @description :
 * @date : 2020-07-03 16:57  
 */
@Data
public class OmsOrderInfoRelation {

    /**
     * 订单
     */
    private OmsOrderInfoDetailVo omsOrderInfoDetailVo;

    /**
     * 订单内的商品
     */
    private List<OmsOrderProductVo> omsOrderProductVos;

}
