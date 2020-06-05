package com.langchao.waveservicemall.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.langchao.waveservicemall.pojo.FlashSale;

import java.util.Date;

/**
 * @Title: FlashSaleService
 * @ProjectName
 * @Description: TODO
 * @author 
 * @date Mon Feb 17 16:34:43 CST 2020
 */
public interface FlashSaleService  {

    /**
     * @author liyuan
     * @Description 添加秒杀控制
     * @param flashSale
     */
    void addFlashSale(FlashSale flashSale);

    /**
     * @author liyuan
     * @Description 修改秒杀控制
     * @param flashSale
     */
    void updateFlashSale(FlashSale flashSale);

    /**
     * @author liyuan
     * @Description  分页查询所有秒杀控制
     * @param page
     * @param size
     * @return
     */
   IPage list(Integer flag, Date startTime, Date endTime, Integer page, Integer size);

    /**
     * @author liyuan
     * @Description 查询秒杀控制
     * @param id
     * @return
     */
    FlashSale selectOne(Integer id);

    void delpl(String checkIds);
}
