package com.liyuan.wave.common.cache.constant;

/**
 * @author liyuan
 * @description
 * @date 2020-06-18 15:14
 */
public interface ProductCache {

    /**
     * 浏览量
     */
    static final String CLICK = "che_constant_click";

    /**
     * 当天的热卖
     */
    static final String HOT_SALE_TODAY = "che_hot_sale_product_current_";

    /**
     * 近7天的热卖
     */
    static final String HOT_SALE_NEAR_SEVEN_DAYS = "che_hot_sale_product_seven_";

    /**
     * 当天的热搜
     */
    static final String HOT_SEARCH_TODAY = "che_hot_search_product_current_";

    /**
     * 近7天的热搜
     */
    static final String HOT_SEARCH_NEAR_SEVEN_DAYS = "che_hot_search_product_seven_";

    /**
     * 所有商品的上架时间
     */
    static final String PUSH_TO_SALE = "che_push_to_sale";

    /**
     * 商品基本信息缓存
     */
    static final String INFO_DETAIL = "che_info_detail";

    /**
     * 商品编码起始值    911111111
     */
    static final String PRODUCT_NUMBER = "che_only_number";


}
