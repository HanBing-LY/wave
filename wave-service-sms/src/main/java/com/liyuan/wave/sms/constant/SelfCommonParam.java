package com.liyuan.wave.sms.constant;

/**
 * @author liyuan
 * @description
 * @email 724837404@qq.com
 * @date 2020-06-17-23:39
 */
public interface SelfCommonParam {

    /**
     * 拼团,秒杀停止标志
     */
    static final Byte IS_DISABLED = 0;

    /**
     * 前端请求:活动已结束
     */
    static final Byte IS_PASSING = -1;

    /**
     * 前端请求:活动进行中
     */
    static final Byte IS_CURRENT = 0;

    /**
     * 前端请求:活动即将开始
     */
    static final Byte IS_WILLING = 1;





    /**
     * 默认初始页码
     */
    static final Long DEFAULT_PAGE_NUMBER = 1L;

    /**
     * 分页最大页面尺寸
     */
    static final Long MAX_PAGE_SIZE = 20L;

    /**
     * 分页默认页面尺寸 (秒杀)
     */
    static final Long FLASH_SALE_DEFAULT_PAGE_SIZE = 10L;

    /**
     * 分页默认页面尺寸 (拼团商品)
     */
    static final Long PRODUCT_CLUB_GROUP_SALE_DEFAULT_PAGE_SIZE = 10L;


    
}
