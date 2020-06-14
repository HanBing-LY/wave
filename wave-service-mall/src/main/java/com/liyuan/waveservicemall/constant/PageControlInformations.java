package com.liyuan.waveservicemall.constant;

/**
 * @Author liyuan
 * @Description 不要使用魔法值
 * @create 2020-03-24-10:54-周二
 */
public interface PageControlInformations {

    /**
     * 默认初始页码
     */
    static final Integer DEFAULT_PAGE_NUMBER = 1;

    /**
     * 分页最大页面尺寸
     */
    static final Integer MAX_PAGE_SIZE = 20;

    /**
     * 分页默认页面尺寸 (秒杀)
     */
    static final Integer FLASH_SALE_DEFAULT_PAGE_SIZE = 10;

    /**
     * 分页默认页面尺寸 (商品详情评论2条)
     */
    static final Integer EVALUATE_DEFAULT_PAGE_SIZE = 2;

    /**
     * 分页默认页面尺寸 (商品首页热卖)
     */
    static final Integer SHOP_HOMEPAGE_HOT_DEFAULT_PAGE_SIZE = 6;

    /**
     * 分页默认页面尺寸 (商品首页最新)
     */
    static final Integer SHOP_HOMEPAGE_NEW_DEFAULT_PAGE_SIZE = 6;

    /**
     * 分页默认页面尺寸 (俱乐部拼团商品)
     */
    static final Integer PRODUCT_CLUB_GROUP_SALE_DEFAULT_PAGE_SIZE = 6;

    /**
     * 分页默认页面尺寸 (超级拼购)
     */
    static final Integer PRODUCT_SUPER_GROUP_SALE_DEFAULT_PAGE_SIZE = 6;

    /**
     * 分页默认页面尺寸 (获得拼团俱乐部)
     */
    static final Integer CLUB_GROUP_SALE_DEFAULT_PAGE_SIZE = 2 ;
}
