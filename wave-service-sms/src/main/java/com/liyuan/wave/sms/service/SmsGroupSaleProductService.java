package com.liyuan.wave.sms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liyuan.wave.common.vo.response.PageInfo;
import com.liyuan.wave.po.sms.SmsGroupSaleProduct;
import com.liyuan.wave.sms.po.vo.SmsGroupSaleProductSaveVo;
import com.liyuan.wave.sms.po.vo.SmsGroupSaleProductVo;

/**
 * @description sms_group_sale_product
 *
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-15 16:09:35
 */
public interface SmsGroupSaleProductService extends IService<SmsGroupSaleProduct> {

    /**
     * @param groupSaleId
     * @description 分页查询控制
     */
    PageInfo<SmsGroupSaleProductVo> listGetByGroupSaleId(Long groupSaleId, Long pageNum, Long pageSize);

    /**
     * @description 选择拼团商品
     * @param smsGroupSaleProductSaveVo
     * @return
     */
    void chooseProduct(SmsGroupSaleProductSaveVo smsGroupSaleProductSaveVo);

    /**
     * @description 停止
     * @param ids
     */
    void disable(String ids);

    /**
     * @description 修改
     * @param smsGroupSaleProductSaveVo
     */
    void updatePrice(SmsGroupSaleProductSaveVo smsGroupSaleProductSaveVo);
}

