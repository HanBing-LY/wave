package com.liyuan.wave.pms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liyuan.wave.pms.po.vo.PmsProductSkuNatureVo;
import com.liyuan.wave.po.pms.PmsProductSkuNature;

/**
 * @description pms_product_sku_nature
 *
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-15 16:10:20
 */
public interface PmsProductSkuNatureService extends IService<PmsProductSkuNature> {

    /**
     * @description 商品选择具有的sku属性值
     * @param pmsProductSkuNatureVo
     * @return
     */
    void saveProductSkuNatureValue(PmsProductSkuNatureVo pmsProductSkuNatureVo);
}

