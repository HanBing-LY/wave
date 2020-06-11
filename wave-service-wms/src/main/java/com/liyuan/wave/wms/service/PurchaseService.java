package com.liyuan.wave.wms.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.liyuan.wave.wms.entity.PurchaseEntity;
import com.liyuan.wave.wms.vo.MergeVo;
import com.liyuan.wave.wms.vo.PurchaseDoneVo;

import java.util.List;

/**
 * 采购信息
 *
 * @author leifengyang
 * @email leifengyang@gmail.com
 * @date 2019-11-17 13:50:10
 */
public interface PurchaseService extends IService<PurchaseEntity> {


    void mergePurchase(MergeVo mergeVo);


    void received(List<Long> ids);


    void done(PurchaseDoneVo doneVo);


}

