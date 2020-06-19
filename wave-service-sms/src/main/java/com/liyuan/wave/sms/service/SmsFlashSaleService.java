package com.liyuan.wave.sms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liyuan.wave.common.vo.response.PageInfo;
import com.liyuan.wave.po.sms.SmsFlashSale;
import com.liyuan.wave.sms.po.vo.SmsFlashSaleSaveVo;
import com.liyuan.wave.sms.po.vo.SmsFlashSaleVo;

/**
 * @author liyuan
 * @description sms_flash_sale
 * @email 724837404@qq.com
 * @date 2020-06-15 16:09:35
 */
public interface SmsFlashSaleService extends IService<SmsFlashSale> {

    /**
     * @param smsFlashSaleSaveVo
     * @author liyuan
     * @description 添加秒杀控制
     */
    void addFlashSale(SmsFlashSaleSaveVo smsFlashSaleSaveVo);

    /**
     * @param smsFlashSaleSaveVo
     * @author liyuan
     * @description 修改秒杀控制
     */
    void updateFlashSale(SmsFlashSaleSaveVo smsFlashSaleSaveVo);

    /**
     * @author liyuan
     * @description 分页查询秒杀控制
     */
    PageInfo<SmsFlashSaleVo> queryPage(Byte status, Long page, Long size);

    /**
     * @param ids
     * @return
     * @author liyuan
     * @description 删除秒杀
     */
    void disable(String ids);
}

