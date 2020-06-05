package com.langchao.waveservicemall.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.langchao.waveservicemall.pojo.dto.ColumnNatureDTO;
import com.langchao.waveservicemall.pojo.vo.FlashSaleProductSkuVo;

import java.util.List;

/**
 * @Title: FlashSaleProductSkuService
 * @ProjectName
 * @Description: TODO
 * @author 
 * @date Mon Feb 17 16:34:43 CST 2020
 */
public interface FlashSaleProductSkuService  {

   IPage list(Integer flashSaleProductId, Integer page, Integer size);

    /**
     * @Author liyuan
     * @Description 分页查询秒杀控制
     * @param flashSaleProductId
     * @return
     */
    List<ColumnNatureDTO> listGetNatureToChoose(Integer flashSaleProductId);

    /**
     * @author liyuan
     * @Description  停止商品秒杀
     * @return
     */
    void delpl(String checkIds);

    /**
     * @Author liyuan
     * @Description 修改秒杀价
     * @return
     */
    void updatePrice(Integer flashSaleProductSkuId, Double price);

    /**
     * @Author liyuan
     * @Description 选择秒杀商品
     * @return
     */
    void chooseProduct(FlashSaleProductSkuVo flashSaleProductSkuVo);
}
