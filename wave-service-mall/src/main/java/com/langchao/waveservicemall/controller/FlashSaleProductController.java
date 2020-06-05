package com.langchao.waveservicemall.controller;

import com.chemguan.business.results.JsonResult;
import com.chemguan.entity.FlashSaleProduct;
import com.chemguan.service.FlashSaleProductService;
import com.langchao.wavecommon.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @author liyuan
 * @create 2020-03-25-13:48-周三
 */

@RestController
@RequestMapping("/flash/sale/product")
public class FlashSaleProductController  extends BaseController {

    @Autowired
    private FlashSaleProductService flashSaleProductService;


    /**
     * @Author liyuan
     * @Description 分页查询秒杀控制
     */
    @GetMapping("/list")
    public JsonResult list(@RequestParam(name = "flashSaleId",required = false)Integer flashSaleId,@RequestParam(name = "productName",required = false)String productName, @RequestParam(name = "flag",required = false)Integer flag, @RequestParam(name = "startTime",required = false) Date startTime, @RequestParam(name = "endTime",required = false) Date endTime,
                       @RequestParam(name = "page",required = false)Integer page, @RequestParam(name = "size",required = false)Integer size){
        return success(flashSaleProductService.list(flashSaleId,productName,flag,startTime,endTime,page,size));
    }

    /**
     * @Author liyuan
     * @Description 停止商品秒杀
     */
    @GetMapping("/delpl")
    public JsonResult delpl(@RequestParam(name = "checkIds",required = true) String checkIds){
        flashSaleProductService.delpl(checkIds);
        return success();
    }

    /**
     * @Author liyuan
     * @Description 商品二级分类
     */
    @GetMapping("/columns")
    public JsonResult columns(){
        return success(flashSaleProductService.columns());
    }

    /**
     * @Author liyuan
     * @Description 商品二级分类id查询商品
     */
    @GetMapping("/columns/products")
    public JsonResult columnsProducts(@RequestParam(name = "columnId",required = true) Integer columnId){
        return success(flashSaleProductService.columnsProducts(columnId));
    }

    /**
     * @Author liyuan
     * @Description 选择秒杀商品
     * @param flashSaleProduct
     * @return
     */
    @PostMapping
    public JsonResult chooseProduct(@RequestBody FlashSaleProduct flashSaleProduct){
        flashSaleProductService.chooseProduct(flashSaleProduct);
        return success();
    }


    /**
     * @Author liyuan
     * @Description 修改秒杀价
     * @return
     */
    @PutMapping("/updatePrice")
    public JsonResult updatePrice(@RequestParam(name = "flashSaleProductId",required = true) Integer flashSaleProductId,@RequestParam(name = "price",required = true) Double price){
        flashSaleProductService.updatePrice(flashSaleProductId,price);
        return success();
    }

    /**
     * @Author liyuan
     * @Description 查询秒杀
     * @return
     */
    @GetMapping("/selectOne")
    public JsonResult selectOne(@RequestParam(name = "id",required = true ) Integer id){
        return success(flashSaleProductService.findById(id));
    }
}
