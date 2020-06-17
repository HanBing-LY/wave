package com.liyuan.wave.pms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liyuan.wave.pms.po.vo.PmsProductInfoVo;
import com.liyuan.wave.po.pms.PmsProductInfo;
import com.liyuan.wavecommon.vo.response.PageInfo;

/**
 * @description pms_product_info
 *
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-15 16:10:20
 */
public interface PmsProductInfoService extends IService<PmsProductInfo> {

    /**
     * @description 查询所有的五级分类下的所有商品
     * @return
     */
    PageInfo<PmsProductInfoVo> listGetAllProductsByMinColumn(Long id);

    /**
     * @description 1-0-0 商城首页热销商品
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<PmsProductInfoVo> listHotSaleList(Integer pageNum, Integer pageSize);

    /**
     * @description 1-0-0 商城首页最新上架商品
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<PmsProductInfoVo> listNewPushList(Integer pageNum, Integer pageSize);
}

