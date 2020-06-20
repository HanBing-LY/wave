package com.liyuan.wave.common.cache.constant;

/**
 * @author liyuan
 * @description
 * @email 724837404@qq.com
 * @date 2020-06-19-22:51
 */
public interface FlashSaleCache {

    /**
     * 所有的秒杀时间段
     */
    static final String ALL_TIME = "che_constant_flash_sale";

    /**
     * 秒杀时间段缓存
     */
    static final String SELF_TIME = "che_flash_sale_time_";

    /**
     * 秒杀时间段缓存
     */
    static final String INFO = "che_flash_sale_info_";

    /**
     * 秒杀时间段下的往商品
     */
    static final String INFO_PRODUCT = "che_flash_sale_info_product_";
}
