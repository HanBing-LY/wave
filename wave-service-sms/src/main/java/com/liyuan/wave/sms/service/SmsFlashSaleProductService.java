package com.liyuan.wave.sms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liyuan.wave.common.vo.response.PageInfo;
import com.liyuan.wave.po.sms.SmsFlashSaleProduct;
import com.liyuan.wave.sms.po.vo.SmsFlashSaleProductSaveVo;
import com.liyuan.wave.sms.po.vo.SmsFlashSaleProductVo;

/**
 * @author liyuan
 * @description sms_flash_sale_product
 * @email 724837404@qq.com
 * @date 2020-06-15 16:09:35
 */
public interface SmsFlashSaleProductService extends IService<SmsFlashSaleProduct> {

    /**
     * @param pageNum
     * @param pageSize
     * @author liyuan
     * @description 分页查询秒杀时间段对应下的秒杀商品
     */
    PageInfo<SmsFlashSaleProductVo> queryByPage(Long flashSaleProductId, Long pageNum, Long pageSize);

    /**
     * @param smsFlashSaleProductSaveVo
     * @author liyuan
     * @description 选择秒杀商品
     */
    void chooseProduct(SmsFlashSaleProductSaveVo smsFlashSaleProductSaveVo);

    /**
     * @param ids 删除的商品对象id
     * @author liyuan
     * @description 停止商品秒杀
     */
    void disable(String ids);

    /**
     * @param smsFlashSaleProductSaveVo
     * @author liyuan
     * @description 修改秒杀商品信息
     */
    void updatePrice(SmsFlashSaleProductSaveVo smsFlashSaleProductSaveVo);
}

