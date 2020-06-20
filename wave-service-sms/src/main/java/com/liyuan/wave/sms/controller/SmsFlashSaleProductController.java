package com.liyuan.wave.sms.controller;

import com.liyuan.wave.common.vo.response.JsonResult;
import com.liyuan.wave.common.web.BaseController;
import com.liyuan.wave.sms.po.vo.SmsFlashSaleProductSaveVo;
import com.liyuan.wave.sms.service.SmsFlashSaleProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @description sms_flash_sale_product
 *
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-15 16:09:35
 */
@RestController
@RequestMapping
public class SmsFlashSaleProductController extends BaseController {

    @Autowired
    private SmsFlashSaleProductService smsFlashSaleProductService;

    /**
     * @author liyuan
     * @description 分页查询秒杀控制
     */
    @GetMapping("/list")
    public JsonResult list(@RequestParam(name = "flashSaleId",required = false) Long flashSaleId, @RequestParam(name = "page",required = false) Long page, @RequestParam(name = "size",required = false) Long size){
        return success(smsFlashSaleProductService.queryByPage(flashSaleId,page,size));
    }


    /**
     * @author liyuan
     * @description 选择秒杀商品
     * @return
     */
    @PostMapping
    public JsonResult chooseProduct(@RequestBody SmsFlashSaleProductSaveVo smsFlashSaleProductSaveVo){
        smsFlashSaleProductService.chooseProduct(smsFlashSaleProductSaveVo);
        return success();
    }

    /**
     * @author liyuan
     * @description 停止商品秒杀
     */
    @GetMapping("/stop")
    public JsonResult stop(@RequestParam(name = "ids",required = true) String ids){
        smsFlashSaleProductService.disable(ids);
        return success();
    }

    /**
     * @author liyuan
     * @description 修改秒杀价
     * @return
     */
    @PutMapping("/update")
    public JsonResult updatePrice(@RequestBody SmsFlashSaleProductSaveVo smsFlashSaleProductSaveVo){
        smsFlashSaleProductService.updatePrice(smsFlashSaleProductSaveVo);
        return success();
    }

}
