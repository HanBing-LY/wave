package com.liyuan.wave.common.cache.constant;

/**
 * @author liyuan
 * @description
 * @email 724837404@qq.com
 * @date 2020-06-19-22:51
 */
public interface GroupSaleCache {

    /**
     * 所有的拼团时间段
     */
    static final String ALL_TIME = "che_constant_group_sale";

    /**
     * 拼团时间段缓存
     */
    static final String SELF_TIME = "che_group_sale_time_";

    /**
     * 拼团时间段缓存
     */
    static final String INFO = "che_group_sale_info_";

    /**
     * 拼团时间段下的商品
     */
    static final String INFO_PRODUCT = "che_group_sale_info_product_";
}
