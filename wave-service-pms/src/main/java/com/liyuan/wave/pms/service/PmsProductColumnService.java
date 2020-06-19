package com.liyuan.wave.pms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liyuan.wave.po.pms.vo.PmsProductColumnSaveVo;
import com.liyuan.wave.po.pms.vo.PmsProductColumnVo;
import com.liyuan.wave.po.pms.PmsProductColumn;

import java.util.List;

/**
 * @description pms_product_column
 *
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-15 16:10:20
 */
public interface PmsProductColumnService extends IService<PmsProductColumn> {

    /**
     * @description 新增商品分类
     * @param productColumnSaveVo
     * @return
     */
    void addProductColumn(PmsProductColumnSaveVo productColumnSaveVo);

    /**
     * @description 保存商品分类名称和图片url
     * @param pmsProductColumnSaveVo
     * @return
     */
    void updateProductColumn(PmsProductColumnSaveVo pmsProductColumnSaveVo);

    /**
     * @description 禁用商品分类
     * @param ids
     * @return
     */
    void disabled(String ids);

    /**
     * @description 查看某一级的所有分类
     * @param id
     * @return
     */
    List<PmsProductColumnVo> getNextChildrenByParentId(Long id);

}

