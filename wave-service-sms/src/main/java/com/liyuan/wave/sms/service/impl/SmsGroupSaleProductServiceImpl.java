package com.liyuan.wave.sms.service.impl;

import com.liyuan.wave.sms.po.vo.GroupSaleProductVo;
import com.liyuan.wave.common.vo.response.PageInfo;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.liyuan.wave.sms.mapper.SmsGroupSaleProductMapper;
import com.liyuan.wave.po.sms.SmsGroupSaleProduct;
import com.liyuan.wave.sms.service.SmsGroupSaleProductService;

/**
 * @description sms_group_sale_product
 *
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-15 16:09:35
 */
@Service("smsGroupSaleProductService")
public class SmsGroupSaleProductServiceImpl extends ServiceImpl<SmsGroupSaleProductMapper, SmsGroupSaleProduct> implements SmsGroupSaleProductService {

    @Override
    public void chooseProduct(GroupSaleProductVo groupSaleProductVo) {

    }

    @Override
    public PageInfo<GroupSaleProductVo> listGetByGroupSaleId(Integer groupSaleId) {
        return null;
    }
}