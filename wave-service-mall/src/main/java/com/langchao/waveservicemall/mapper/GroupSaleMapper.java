package com.langchao.waveservicemall.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.langchao.waveservicemall.pojo.GroupSale;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface GroupSaleMapper extends BaseMapper<GroupSale> {

    List<GroupSale> list(Integer flag, Date startTime, Date endTime);

    /**
     * @author liyuan
     * @Description 批量删除
     * @param ids
     */
    void deleteByGroupSaleIds(@Param("groupSaleIds") List<Integer> ids);
}