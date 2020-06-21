package com.liyuan.wave.sms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liyuan.wave.common.vo.response.PageInfo;
import com.liyuan.wave.po.sms.SmsGroupSale;
import com.liyuan.wave.sms.po.vo.SmsGroupSaleSaveVo;
import com.liyuan.wave.sms.po.vo.SmsGroupSaleVo;

/**
 * @author liyuan
 * @description sms_group_sale
 * @email 724837404@qq.com
 * @date 2020-06-15 16:09:35
 */
public interface SmsGroupSaleService extends IService<SmsGroupSale> {

    /**
     * @param smsGroupSaleSaveVo
     * @description 添加拼团控制
     */
    void add(SmsGroupSaleSaveVo smsGroupSaleSaveVo);

    /**
     * @param smsGroupSaleSaveVo
     * @description 修改拼团控制
     */
    void modify(SmsGroupSaleSaveVo smsGroupSaleSaveVo);

    /**
     * @param status
     * @param pageNum
     * @param pageSize
     * @description 分页查询拼团控制
     */
    PageInfo<SmsGroupSaleVo> queryPage(Byte status, Long pageNum, Long pageSize);

    /**
     * @param ids
     * @description 删除拼团
     */
    void disable(String ids);
}

