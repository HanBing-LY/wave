package com.liyuan.wave.pms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liyuan.wave.po.pms.PmsProductColumn;

/**
 * @description pms_product_column
 *
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-15 16:10:20
 */
public interface PmsProductColumnService extends IService<PmsProductColumn> {

    void add(PmsProductColumn pmsProductColumn);
}

