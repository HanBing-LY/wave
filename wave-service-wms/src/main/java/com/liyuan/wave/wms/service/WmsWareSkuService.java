package com.liyuan.wave.wms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liyuan.wave.po.wms.WmsWareSku;
import com.liyuan.wave.po.wms.vo.WmsWareSkuSaveVo;
import com.liyuan.wave.po.wms.vo.WmsWareSkuVo;

/**
 * @author liyuan
 * @description
 * @date 2020-06-15 15:46
 */
public interface WmsWareSkuService extends IService<WmsWareSku> {

    /**
     * @description 查商品库存信息
     * @param articleNumber
     * @return
     */
    WmsWareSkuVo queryByArticleNumber(String articleNumber);

    /**
     * @description 扣减商品库存
     * @param wmsWareSkuSaveVo
     * @return
     */
    void decrement(WmsWareSkuSaveVo wmsWareSkuSaveVo);
}
