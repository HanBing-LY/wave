package com.langchao.waveservicemall.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.langchao.waveservicemall.pojo.GroupSaleProductSku;
import com.langchao.waveservicemall.pojo.vo.GroupSaleProductSkuVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GroupSaleProductSkuMapper extends BaseMapper<GroupSaleProductSku> {

    /**
     * @author liyuan
     * @Description 删除商品秒杀
     * @param groupSaleProductIds
     */
    void deleteByProductSkuIds(@Param("groupSaleProductIds") List<Integer> groupSaleProductIds);

    /**
     * @author liyuan
     * @Description 分页查询控制器
     * @param groupSaleProductId
     * @return
     */
    List<GroupSaleProductSkuVo> list(@Param("groupSaleProductId") Integer groupSaleProductId);

    /**
     * @author liyuan
     * @Description 根据groupsaleid 查询
     * @param groupSaleIds
     * @return
     */
    List<GroupSaleProductSku> selectByGroupSaleIds(@Param("groupSaleIds") List<Integer> groupSaleIds);
}