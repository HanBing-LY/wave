package com.langchao.waveservicemall.controller;


import com.langchao.wavecommon.vo.response.JsonResult;
import com.langchao.wavecommon.web.BaseController;
import com.langchao.waveservicemall.pojo.vo.FlashSaleProductSkuVo;
import com.langchao.waveservicemall.service.FlashSaleProductSkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author liyuan
 * @create 2020-03-27-10:16-周五
 */
@RestController
@RequestMapping("/flash/sale/product/sku")
public class FlashSaleProductSkuController extends BaseController {

    @Autowired
    private FlashSaleProductSkuService flashSaleProductSkuService;

    /**
     * @Author liyuan
     * @Description 分页查询秒杀控制
     */
    @GetMapping("/list")
    public JsonResult list(@RequestParam(name = "flashSaleProductId", required = false) Integer flashSaleProductId, @RequestParam(name = "page", required = false) Integer page, @RequestParam(name = "size", required = false) Integer size) {
        return success(flashSaleProductSkuService.list(flashSaleProductId, page, size));
    }

    /**
     * @Author liyuan
     * @Description 分页查询秒杀控制
     */
    @GetMapping("/listGetNatureToChoose")
    public JsonResult listGetNatureToChoose(@RequestParam(name = "flashSaleProductId", required = true) Integer flashSaleProductId) {
        return success(flashSaleProductSkuService.listGetNatureToChoose(flashSaleProductId));
    }

    /**
     * @Author liyuan
     * @Description 停止商品秒杀
     */
    @GetMapping("/delpl")
    public JsonResult delpl(@RequestParam(name = "checkIds", required = true) String checkIds) {
        flashSaleProductSkuService.delpl(checkIds);
        return success();
    }

    /**
     * @return
     * @Author liyuan
     * @Description 修改秒杀价
     */
    @PutMapping("/updatePrice")
    public JsonResult updatePrice(@RequestParam(name = "flashSaleProductSkuId", required = true) Integer flashSaleProductSkuId, @RequestParam(name = "price", required = true) Double price) {
        flashSaleProductSkuService.updatePrice(flashSaleProductSkuId, price);
        return success();
    }

    /**
     * @return
     * @Author liyuan
     * @Description 选择秒杀商品
     */
    @PostMapping
    public JsonResult chooseProduct(@RequestBody FlashSaleProductSkuVo flashSaleProductSkuVo) {
        flashSaleProductSkuService.chooseProduct(flashSaleProductSkuVo);
        return success();
    }
}
