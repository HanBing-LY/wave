package com.liyuan.wave.sms.service.impl;

import com.liyuan.wave.sms.po.vo.SmsFlashSaleVo;
import com.liyuan.wavecommon.vo.response.PageInfo;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.liyuan.wave.sms.mapper.SmsFlashSaleMapper;
import com.liyuan.wave.po.sms.SmsFlashSale;
import com.liyuan.wave.sms.service.SmsFlashSaleService;

import java.util.Date;

/**
 * @description sms_flash_sale
 *
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-15 16:09:35
 */
@Service("smsFlashSaleService")
public class SmsFlashSaleServiceImpl extends ServiceImpl<SmsFlashSaleMapper, SmsFlashSale> implements SmsFlashSaleService {

    @Override
    public void addFlashSale(SmsFlashSale smsFlashSale) {

    }

    @Override
    public void updateFlashSale(SmsFlashSale smsFlashSale) {

    }

    @Override
    public PageInfo<SmsFlashSaleVo> queryPage(Integer flag, Date startTime, Date endTime, Integer page, Integer size) {
        return null;
    }

    @Override
    public void delpl(String ids) {

    }
}