package com.liyuan.wave.sms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liyuan.wave.po.sms.SmsGroupSaleProduct;
import com.liyuan.wave.sms.po.vo.GroupSaleProductVo;
import com.liyuan.wavecommon.vo.response.PageInfo;

/**
 * @description sms_group_sale_product
 *
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-15 16:09:35
 */
public interface SmsGroupSaleProductService extends IService<SmsGroupSaleProduct> {

    /**
     * @author liyuan
     * @description 选择拼团商品
     * @param groupSaleProductVo
     * @return
     */
    void chooseProduct(GroupSaleProductVo groupSaleProductVo);

    /**
     * @author liyuan
     * @description 分页查询控制
     */
    PageInfo<GroupSaleProductVo> listGetByGroupSaleId(Integer groupSaleId);
}

