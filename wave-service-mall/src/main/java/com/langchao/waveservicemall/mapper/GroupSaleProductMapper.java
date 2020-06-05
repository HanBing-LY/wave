package com.langchao.waveservicemall.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.langchao.waveservicemall.pojo.GroupSaleProduct;
import com.langchao.waveservicemall.pojo.vo.GroupSaleVo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface GroupSaleProductMapper extends BaseMapper<GroupSaleProduct> {

    List<GroupSaleProduct> getProductGroupSalePriceAndPeopleNumber(Integer productId);

    /**
     * @author liyuan
     * @Description 分页查询控制器
     * @param flag
     * @param startTime
     * @param endTime
     * @return
     */
    List<GroupSaleVo> list(@Param("groupSaleId") Integer groupSaleId, @Param("productName") String productName, @Param("flag") Integer flag, @Param("startTime") Date startTime, @Param("endTime") Date endTime);

    /**
     * @author liyuan
     * @Description 删除商品拼团
     * @param groupSaleProductIds
     */
    void deleteByProductSkuIds(@Param("groupSaleProductIds") List<Integer> groupSaleProductIds);

    /**
     * @author liyuan
     * @Description 根据拼团控制id删除关联信息
     * @param groupSaleIds
     */
    void deleteByGroupSaleIds(@Param("groupSaleIds") List<Integer> groupSaleIds);
}