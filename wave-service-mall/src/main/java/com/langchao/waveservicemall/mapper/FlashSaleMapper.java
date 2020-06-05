package com.langchao.waveservicemall.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.langchao.waveservicemall.pojo.FlashSale;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface FlashSaleMapper extends BaseMapper<FlashSale> {

    /**
     * @author liyuan
     * @Description 分页查询控制器
     * @param flag
     * @param startTime
     * @param endTime
     * @return
     */
    List<FlashSale> list(@Param("flag") Integer flag, @Param("startTime") Date startTime, @Param("endTime") Date endTime);


    /**
     * @description 查询正在秒杀的活动
     * @param
     * @return
     **/
    FlashSale getCurrentFlashSale();

    /**
     * @description 查询下期秒杀的活动
     * @param
     * @return
     **/
    FlashSale getNextFlashSale();

    /**
     * @author liyuan
     * @Description 批量删除
     * @param flashSaleIds
     */
    void deleteByFlashSaleIds(@Param("flashSaleIds") List<Integer> flashSaleIds);
}