package com.liyuan.wave.sms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liyuan.wave.common.vo.response.PageInfo;
import com.liyuan.wave.po.sms.SmsGroupSale;
import com.liyuan.wave.sms.po.vo.SmsGroupSaleSaveVo;
import com.liyuan.wave.sms.po.vo.SmsGroupSaleVo;

/**
 * @description sms_group_sale
 *
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-15 16:09:35
 */
public interface SmsGroupSaleService extends IService<SmsGroupSale> {

    /**
     * @author liyuan
     * @description 添加拼团控制
     * @param smsGroupSaleSaveVo
     */
    void add(SmsGroupSaleSaveVo smsGroupSaleSaveVo);

    /**
     * @author liyuan
     * @description 修改拼团控制
     * @param smsGroupSaleSaveVo
     */
    void modify(SmsGroupSaleSaveVo smsGroupSaleSaveVo);

    /**
     * @author liyuan
     * @description 分页查询拼团控制
     */
    PageInfo<SmsGroupSaleVo> queryPage(Byte status, Long pageNum, Long pageSize);

    /**
     * @author liyuan
     * @description 删除拼团
     * @param ids
     * @return
     */
    void disable(String ids);
}

