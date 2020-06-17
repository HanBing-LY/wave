package com.liyuan.wave.sms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liyuan.wave.po.sms.SmsFlashSaleProduct;
import com.liyuan.wave.sms.po.vo.FlashSaleProductVo;
import com.liyuan.wave.sms.po.vo.SmsFlashSaleProductVo;
import com.liyuan.wavecommon.vo.response.PageInfo;

import java.math.BigDecimal;

/**
 * @description sms_flash_sale_product
 *
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-15 16:09:35
 */
public interface SmsFlashSaleProductService extends IService<SmsFlashSaleProduct> {

    /**
     * @author liyuan
     * @description 分页查询秒杀控制
     */
    PageInfo<SmsFlashSaleProductVo> queryByPage(Integer flashSaleProductId, Integer page, Integer size);

    /**
     * @author liyuan
     * @description 选择秒杀商品
     * @return
     */
    void chooseProduct(FlashSaleProductVo flashSaleProductVo);

    /**
     * @author liyuan
     * @description 停止商品秒杀
     */
    void delpl(String ids);

    /**
     * @author liyuan
     * @description 修改秒杀价
     * @return
     */
    void updatePrice(Integer flashSaleProductSkuId, BigDecimal price);
}

