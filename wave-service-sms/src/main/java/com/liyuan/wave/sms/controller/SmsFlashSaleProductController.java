package com.liyuan.wave.sms.controller;

import com.liyuan.wave.sms.po.vo.FlashSaleProductVo;
import com.liyuan.wave.sms.service.SmsFlashSaleProductService;
import com.liyuan.wavecommon.vo.response.JsonResult;
import com.liyuan.wavecommon.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;


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
    public JsonResult list(@RequestParam(name = "flashSaleProductId",required = false) Integer flashSaleProductId, @RequestParam(name = "page",required = false) Integer page, @RequestParam(name = "size",required = false) Integer size){
        return success(smsFlashSaleProductService.queryByPage(flashSaleProductId,page,size));
    }


    /**
     * @author liyuan
     * @description 选择秒杀商品
     * @return
     */
    @PostMapping
    public JsonResult chooseProduct(@RequestBody FlashSaleProductVo flashSaleProductVo){
        smsFlashSaleProductService.chooseProduct(flashSaleProductVo);
        return success();
    }

    /**
     * @author liyuan
     * @description 停止商品秒杀
     */
    @GetMapping("/delpl")
    public JsonResult delpl(@RequestParam(name = "ids",required = true) String ids){
        smsFlashSaleProductService.delpl(ids);
        return success();
    }

    /**
     * @author liyuan
     * @description 修改秒杀价
     * @return
     */
    @PutMapping("/updatePrice")
    public JsonResult updatePrice(@RequestParam(name = "flashSaleProductSkuId",required = true) Integer flashSaleProductSkuId,@RequestParam(name = "price",required = true) BigDecimal price){
        smsFlashSaleProductService.updatePrice(flashSaleProductSkuId,price);
        return success();
    }

}
