package com.liyuan.wave.sms.controller;

import com.liyuan.wave.common.vo.response.JsonResult;
import com.liyuan.wave.common.web.BaseController;
import com.liyuan.wave.sms.po.vo.SmsGroupSaleProductSaveVo;
import com.liyuan.wave.sms.service.SmsGroupSaleProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @description sms_group_sale_product
 *
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-15 16:09:35
 */
@RestController
@RequestMapping
public class SmsGroupSaleProductController extends BaseController {

    @Autowired
    private SmsGroupSaleProductService smsGroupSaleProductService;

    /**
     * @author liyuan
     * @description 选择拼团商品
     * @param smsGroupSaleProductSaveVo
     * @return
     */
    @PostMapping
    public JsonResult chooseProduct(@RequestBody SmsGroupSaleProductSaveVo smsGroupSaleProductSaveVo){
        smsGroupSaleProductService.chooseProduct(smsGroupSaleProductSaveVo);
        return success();
    }


    /**
     * @author liyuan
     * @description 分页查询控制
     */
    @GetMapping("/list")
    public JsonResult list(@RequestParam(name = "groupSaleId",required = true) Long groupSaleId, @RequestParam(name = "page",required = false) Long page, @RequestParam(name = "size",required = false) Long size){
        return success(smsGroupSaleProductService.listGetByGroupSaleId(groupSaleId,page,size));
    }


    /**
     * @author liyuan
     * @description 停止商品
     */
    @GetMapping("/stop")
    public JsonResult stop(@RequestParam(name = "ids",required = true) String ids){
        smsGroupSaleProductService.disable(ids);
        return success();
    }

    /**
     * @author liyuan
     * @description 修改
     * @return
     */
    @PutMapping("/update")
    public JsonResult updatePrice(@RequestBody SmsGroupSaleProductSaveVo smsGroupSaleProductSaveVo){
        smsGroupSaleProductService.updatePrice(smsGroupSaleProductSaveVo);
        return success();
    }
}
