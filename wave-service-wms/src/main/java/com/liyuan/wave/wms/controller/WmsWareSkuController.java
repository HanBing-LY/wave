package com.liyuan.wave.wms.controller;

import com.liyuan.wave.common.vo.response.JsonResult;
import com.liyuan.wave.common.web.BaseController;
import com.liyuan.wave.po.wms.vo.WmsWareSkuSaveVo;
import com.liyuan.wave.wms.service.WmsWareSkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author liyuan
 * @description
 * @date 2020-06-15 15:47
 */
@RestController
@RequestMapping("/wms/ware/sku")
public class WmsWareSkuController extends BaseController {

    @Autowired
    private WmsWareSkuService wmsWareSkuService;

    /**
     * @description 查商品库存信息
     * @param articleNumber
     * @return
     */
    @GetMapping
    public JsonResult queryByArticleNumber(@RequestBody String articleNumber){
        return success(wmsWareSkuService.queryByArticleNumber(articleNumber));
    }

    /**
     * @description 扣减商品库存
     * @param wmsWareSkuSaveVo
     * @return
     */
    @PutMapping("/decrement")
    public JsonResult decrement(@RequestBody WmsWareSkuSaveVo wmsWareSkuSaveVo){
        wmsWareSkuService.decrement(wmsWareSkuSaveVo);
        return success();
    }


}
