package com.liyuan.wave.sms.service.impl;

import com.liyuan.wave.sms.po.vo.SmsGroupSaleVo;
import com.liyuan.wave.common.vo.response.PageInfo;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.liyuan.wave.sms.mapper.SmsGroupSaleMapper;
import com.liyuan.wave.po.sms.SmsGroupSale;
import com.liyuan.wave.sms.service.SmsGroupSaleService;

import java.util.Date;

/**
 * @description sms_group_sale
 *
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-15 16:09:35
 */
@Service("smsGroupSaleService")
public class SmsGroupSaleServiceImpl extends ServiceImpl<SmsGroupSaleMapper, SmsGroupSale> implements SmsGroupSaleService {

    @Override
    public void add(SmsGroupSale smsGroupSale) {

    }

    @Override
    public void modify(SmsGroupSale smsGroupSale) {

    }

    @Override
    public PageInfo<SmsGroupSaleVo> queryPage(Integer flag, Date startTime, Date endTime, Integer page, Integer size) {
        return null;
    }

    @Override
    public void delpl(String ids) {

    }
}