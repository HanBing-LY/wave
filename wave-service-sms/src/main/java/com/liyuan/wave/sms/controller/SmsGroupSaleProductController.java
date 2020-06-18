package com.liyuan.wave.sms.controller;

import com.liyuan.wave.sms.po.vo.GroupSaleProductVo;
import com.liyuan.wave.sms.service.SmsGroupSaleProductService;
import com.liyuan.wave.common.vo.response.JsonResult;
import com.liyuan.wave.common.web.BaseController;
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
     * @param groupSaleProductVo
     * @return
     */
    @PostMapping
    public JsonResult chooseProduct(@RequestBody GroupSaleProductVo groupSaleProductVo){
        smsGroupSaleProductService.chooseProduct(groupSaleProductVo);
        return success();
    }


    /**
     * @author liyuan
     * @description 分页查询控制
     */
    @GetMapping("/list")
    public JsonResult list(@RequestParam(name = "groupSaleId",required = true) Integer groupSaleId){
        return success(smsGroupSaleProductService.listGetByGroupSaleId(groupSaleId));
    }
}
