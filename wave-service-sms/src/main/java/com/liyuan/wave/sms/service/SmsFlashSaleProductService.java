package com.liyuan.wave.sms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liyuan.wave.common.vo.response.PageInfo;
import com.liyuan.wave.po.sms.SmsFlashSaleProduct;
import com.liyuan.wave.sms.po.vo.SmsFlashSaleProductSaveVo;
import com.liyuan.wave.sms.po.vo.SmsFlashSaleProductVo;

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
    PageInfo<SmsFlashSaleProductVo> queryByPage(Long flashSaleProductId, Long page, Long size);

    /**
     * @author liyuan
     * @description 选择秒杀商品
     * @return
     */
    void chooseProduct(SmsFlashSaleProductSaveVo smsFlashSaleProductSaveVo);

    /**
     * @author liyuan
     * @description 停止商品秒杀
     */
    void disable(String ids);

    /**
     * @author liyuan
     * @description 修改秒杀价
     * @return
     */
    void updatePrice(SmsFlashSaleProductSaveVo smsFlashSaleProductSaveVo);
}

