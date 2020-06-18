package com.liyuan.wave.sms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liyuan.wave.po.sms.SmsGroupSale;
import com.liyuan.wave.sms.po.vo.SmsGroupSaleVo;
import com.liyuan.wave.common.vo.response.PageInfo;

import java.util.Date;

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
     * @param smsGroupSale
     */
    void add(SmsGroupSale smsGroupSale);

    /**
     * @author liyuan
     * @description 修改拼团控制
     * @param smsGroupSale
     */
    void modify(SmsGroupSale smsGroupSale);

    /**
     * @author liyuan
     * @description 分页查询秒杀控制
     */
    PageInfo<SmsGroupSaleVo> queryPage(Integer flag, Date startTime, Date endTime, Integer page, Integer size);

    /**
     * @author liyuan
     * @description 删除拼团
     * @param ids
     * @return
     */
    void delpl(String ids);
}

