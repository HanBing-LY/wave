package com.liyuan.wave.pms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liyuan.wave.pms.po.vo.PmsProductInfoVo;
import com.liyuan.wave.po.pms.PmsProductInfo;
import com.liyuan.wave.common.vo.response.PageInfo;

import java.util.List;

/**
 * @author liyuan
 * @description pms_product_info
 * @email 724837404@qq.com
 * @date 2020-06-15 16:10:20
 */
public interface PmsProductInfoService extends IService<PmsProductInfo> {

    /**
     * @return
     * @description 查询所有的五级分类下的所有商品
     */
    List<PmsProductInfoVo> listGetAllProductsByMinColumn(Long id, Integer pageNum, Integer pageSize);

    /**
     * @param pageNum
     * @param pageSize
     * @return
     * @description 1-0-0 商城首页热销商品
     */
    PageInfo<PmsProductInfoVo> listHotSaleList(Integer pageNum, Integer pageSize);

    /**
     * @param pageNum
     * @param pageSize
     * @return
     * @description 热搜商品
     */
    PageInfo<PmsProductInfoVo> hotSearchList(Integer pageNum, Integer pageSize);

    /**
     * @param pageNum
     * @param pageSize
     * @return
     * @description 1-0-0 商城首页最新上架商品
     */
    PageInfo<PmsProductInfoVo> listNewPushList(Integer pageNum, Integer pageSize);

    /**
     * @param pageNum
     * @param pageSize
     * @return
     * @description 明星商品
     */
    PageInfo<PmsProductInfoVo> starList(Integer pageNum, Integer pageSize);
}

