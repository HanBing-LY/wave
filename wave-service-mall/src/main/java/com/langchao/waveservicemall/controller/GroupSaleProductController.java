package com.langchao.waveservicemall.controller;


import com.langchao.wavecommon.vo.response.JsonResult;
import com.langchao.wavecommon.web.BaseController;
import com.langchao.waveservicemall.pojo.GroupSaleProduct;
import com.langchao.waveservicemall.service.GroupSaleProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @author liyuan
 * @create 2020-03-26-15:40-周四
 */
@RestController
@RequestMapping("/group/sale/product")
public class GroupSaleProductController  extends BaseController {

    /**
     * 组件注入
     */
    @Autowired
    private GroupSaleProductService groupSaleProductService;


    /**
     * @Author liyuan
     * @Description 分页查询秒杀控制
     */
    @GetMapping("/list")
    public JsonResult list(@RequestParam(name = "groupSaleId",required = false)Integer groupSaleId, @RequestParam(name = "productName",required = false)String productName, @RequestParam(name = "flag",required = false)Integer flag, @RequestParam(name = "startTime",required = false) Date startTime, @RequestParam(name = "endTime",required = false) Date endTime,
                           @RequestParam(name = "page",required = false)Integer page, @RequestParam(name = "size",required = false)Integer size){
        return success(groupSaleProductService.list(groupSaleId,productName,flag,startTime,endTime,page,size));
    }

    /**
     * @Author liyuan
     * @Description 停止商品秒杀
     */
    @GetMapping("/delpl")
    public JsonResult delpl(@RequestParam(name = "checkIds",required = true) String checkIds){
        groupSaleProductService.delpl(checkIds);
        return success();
    }

    /**
     * @Author liyuan
     * @Description 商品二级分类
     */
    @GetMapping("/columns")
    public JsonResult columns(){
        return success(groupSaleProductService.columns());
    }

    /**
     * @Author liyuan
     * @Description 商品二级分类id查询商品
     */
    @GetMapping("/columns/products")
    public JsonResult columnsProducts(@RequestParam(name = "columnId",required = true) Integer columnId){
        return success(groupSaleProductService.columnsProducts(columnId));
    }

    /**
     * @Author liyuan
     * @Description 选择秒杀商品
     * @param groupSaleProduct
     * @return
     */
    @PostMapping
    public JsonResult chooseProduct(@RequestBody GroupSaleProduct groupSaleProduct){
        groupSaleProductService.chooseProduct(groupSaleProduct);
        return success();
    }

    /**
     * @Author liyuan
     * @Description 查询秒杀
     * @return
     */
    @GetMapping("/selectOne")
    public JsonResult selectOne(@PathVariable(name = "id",required = true) Integer id){
        return success(groupSaleProductService.getById(id));
    }

    /**
     * @Author liyuan
     * @Description 修改秒杀价
     * @return
     */
    @PutMapping("/updatePrice")
    public JsonResult updatePrice(@RequestParam(name = "groupSaleProductId",required = true) Integer groupSaleProductId,@RequestParam(name = "price",required = true) Double price){
        groupSaleProductService.updatePrice(groupSaleProductId,price);
        return success();
    }

}
