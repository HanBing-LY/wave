package com.langchao.waveservicemall.controller;

import com.chemguan.business.results.JsonResult;
import com.chemguan.service.GroupSaleProductSkuService;
import com.chemguan.vo.GroupSaleProductSkuVo;
import com.langchao.wavecommon.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author liyuan
 * @create 2020-03-27-10:16-周五
 */
@RestController
@RequestMapping("/group/sale/product/sku")
public class GroupSaleProductSkuController  extends BaseController {

    @Autowired
    private GroupSaleProductSkuService groupSaleProductSkuService;

    /**
     * @Author liyuan
     * @Description 分页查询控制
     */
    @GetMapping("/list")
    public JsonResult list(@RequestParam(name = "groupSaleProductId",required = false) Integer groupSaleProductId,@RequestParam(name = "page",required = false) Integer page,@RequestParam(name = "size",required = false) Integer size){
        return success(groupSaleProductSkuService.list(groupSaleProductId,page,size));
    }

    /**
     * @Author liyuan
     * @Description 分页查询控制
     */
    @GetMapping("/listGetNatureToChoose")
    public JsonResult listGetNatureToChoose(@RequestParam(name = "groupSaleProductId",required = true) Integer groupSaleProductId){
        return success(groupSaleProductSkuService.listGetNatureToChoose(groupSaleProductId));
    }

    /**
     * @Author liyuan
     * @Description 停止商品
     */
    @GetMapping("/delpl")
    public JsonResult delpl(@RequestParam(name = "checkIds",required = true) String checkIds){
        groupSaleProductSkuService.delpl(checkIds);
        return success();
    }

    /**
     * @Author liyuan
     * @Description 修改价
     * @return
     */
    @PutMapping("/updatePrice")
    public JsonResult updatePrice(@RequestParam(name = "groupSaleProductSkuId",required = true) Integer groupSaleProductSkuId,@RequestParam(name = "price",required = true) Double price){
        groupSaleProductSkuService.updatePrice(groupSaleProductSkuId,price);
        return success();
    }

    /**
     * @Author liyuan
     * @Description 选择商品
     * @return
     */
    @PostMapping
    public JsonResult chooseProduct(@RequestBody GroupSaleProductSkuVo groupSaleProductSkuVo){
        groupSaleProductSkuService.chooseProduct(groupSaleProductSkuVo);
        return success();
    }
}
