package com.liyuan.wave.sms.controller;

import com.liyuan.wave.sms.po.vo.SmsGroupSaleSaveVo;
import com.liyuan.wave.sms.service.SmsGroupSaleService;
import com.liyuan.wave.common.vo.response.JsonResult;
import com.liyuan.wave.common.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @description sms_group_sale
 *
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-15 16:09:35
 */
@RestController
@RequestMapping
public class SmsGroupSaleController extends BaseController {

    @Autowired
    private SmsGroupSaleService smsGroupSaleService;

    /**
     * @author liyuan
     * @description 添加拼团控制
     * @param smsGroupSaleSaveVo
     */
    @PostMapping
    public JsonResult add(@RequestBody SmsGroupSaleSaveVo smsGroupSaleSaveVo){
        smsGroupSaleService.add(smsGroupSaleSaveVo);
        return success();
    }

    /**
     * @author liyuan
     * @description 修改拼团控制
     * @param smsGroupSaleSaveVo
     */
    @PutMapping
    public JsonResult update(@RequestBody SmsGroupSaleSaveVo smsGroupSaleSaveVo){
        smsGroupSaleService.modify(smsGroupSaleSaveVo);
        return success();
    }

    /**
     * @author liyuan
     * @description 分页查询秒杀控制
     */
    @GetMapping("/list")
    public JsonResult list(@RequestParam(name = "status", required = false) Byte status,
                           @RequestParam(name = "page", required = false) Long page, @RequestParam(name = "size", required = false) Long size){
        return success(smsGroupSaleService.queryPage(status,page,size));
    }

    /**
     * @author liyuan
     * @description 删除拼团
     * @param ids
     * @return
     */
    @GetMapping("/delpl")
    public JsonResult delpl(@RequestParam(name = "ids",required = true ) String ids){
        smsGroupSaleService.disable(ids);
        return success();
    }

}
