package com.liyuan.wave.pms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liyuan.wave.po.pms.PmsProductSku;
import com.liyuan.wave.po.pms.vo.PmsProductSkuVo;

/**
 * @description pms_product_sku
 *
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-15 16:10:20
 */
public interface PmsProductSkuService extends IService<PmsProductSku> {

    /**
     * @description 商品sku对应商品的信息
     * @param articleNumber
     * @return
     */
    PmsProductSkuVo getDetailByArticleNumber(String articleNumber);
}

