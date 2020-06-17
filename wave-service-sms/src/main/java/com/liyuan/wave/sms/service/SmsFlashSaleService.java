package com.liyuan.wave.sms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liyuan.wave.po.sms.SmsFlashSale;
import com.liyuan.wave.sms.po.vo.SmsFlashSaleVo;
import com.liyuan.wavecommon.vo.response.PageInfo;

import java.util.Date;

/**
 * @author liyuan
 * @description sms_flash_sale
 * @email 724837404@qq.com
 * @date 2020-06-15 16:09:35
 */
public interface SmsFlashSaleService extends IService<SmsFlashSale> {

    /**
     * @param smsFlashSale
     * @author liyuan
     * @description 添加秒杀控制
     */
    void addFlashSale(SmsFlashSale smsFlashSale);

    /**
     * @param smsFlashSale
     * @author liyuan
     * @description 修改秒杀控制
     */
    void updateFlashSale(SmsFlashSale smsFlashSale);

    /**
     * @author liyuan
     * @description 分页查询秒杀控制
     */
    PageInfo<SmsFlashSaleVo> queryPage(Integer flag, Date startTime, Date endTime, Integer page, Integer size);

    /**
     * @param ids
     * @return
     * @author liyuan
     * @description 删除秒杀
     */
    void delpl(String ids);
}

