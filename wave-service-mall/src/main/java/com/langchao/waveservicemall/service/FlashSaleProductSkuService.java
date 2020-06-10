package com.langchao.waveservicemall.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.langchao.waveservicemall.pojo.FlashSaleProductSku;
import com.langchao.waveservicemall.pojo.vo.FlashSaleProductSkuVo;

/**
 * @Title: FlashSaleProductSkuService
 * @ProjectName
 * @Description: TODO
 * @author 
 * @date Mon Feb 17 16:34:43 CST 2020
 */
public interface FlashSaleProductSkuService extends IService<FlashSaleProductSku> {

   IPage list(Integer flashSaleProductId, Integer page, Integer size);

    /**
     * @Author liyuan
     * @Description 选择秒杀商品
     * @return
     */
    void chooseProduct(FlashSaleProductSkuVo flashSaleProductSkuVo);
}
