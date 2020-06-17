package com.liyuan.waveserviceapi.pms;


import com.liyuan.wavecommon.vo.response.JsonResult;

/**
 * @description pms_product_column
 *
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-15 16:10:20
 */
public interface PmsProductColumnControllerApi {

    /**
     * @description 查询所有的五级分类下的所有商品
     * @return
     */
    JsonResult listGetAllProductsByMinColumn();
}
