package com.liyuan.wave.common.cache.constant;

/**
 * @author liyuan
 * @description
 * @email 724837404@qq.com
 * @date 2020-06-19-22:51
 */
public interface GroupSaleCache {

    /**
     * 拼团商品布隆过滤器
     */
    static final String BLOOM_FILTER = "che_constant_group_sale_product_bloomFilter";


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

    /**
     * 每个商品下的团队
     */
    static final String GROUP_TEAM_OF_PRODUCT = "che_group_sale_team_of_product_";

    /**
     * 团队
     */
    static final String GROUP_MEMBERS_OF_TEAM = "che_group_sale_members_of_team_";
}
