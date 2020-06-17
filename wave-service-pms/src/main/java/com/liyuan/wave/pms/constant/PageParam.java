package com.liyuan.wave.pms.constant;

/**
 * @author liyuan
 * @description
 * @email 724837404@qq.com
 * @date 2020-06-17-23:39
 */
public interface PageParam {

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
     * 分页默认页面尺寸 (商品详情评论2条)
     */
    static final Long EVALUATE_DEFAULT_PAGE_SIZE = 2L;

    /**
     * 分页默认页面尺寸 (商品首页热卖)
     */
    static final Long SHOP_HOMEPAGE_HOT_DEFAULT_PAGE_SIZE = 6L;

    /**
     * 分页默认页面尺寸 (商品首页最新)
     */
    static final Long SHOP_HOMEPAGE_NEW_DEFAULT_PAGE_SIZE = 6L;

    /**
     * 分页默认页面尺寸 (拼团商品)
     */
    static final Long PRODUCT_CLUB_GROUP_SALE_DEFAULT_PAGE_SIZE = 6L;

    /**
     * 分页默认页面尺寸 (获得拼团俱乐部)
     */
    static final Long CLUB_GROUP_SALE_DEFAULT_PAGE_SIZE = 2L ;
    
}
