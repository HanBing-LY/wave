package com.liyuan.wave.sms.controller;

import com.liyuan.wave.po.sms.SmsGroupSale;
import com.liyuan.wave.sms.service.SmsGroupSaleService;
import com.liyuan.wavecommon.vo.response.JsonResult;
import com.liyuan.wavecommon.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


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
     * @param smsGroupSale
     */
    @PostMapping
    public JsonResult add(@RequestBody SmsGroupSale smsGroupSale){
        smsGroupSaleService.add(smsGroupSale);
        return success();
    }

    /**
     * @author liyuan
     * @description 修改拼团控制
     * @param smsGroupSale
     */
    @PutMapping
    public JsonResult update(@RequestBody SmsGroupSale smsGroupSale){
        smsGroupSaleService.modify(smsGroupSale);
        return success();
    }

    /**
     * @author liyuan
     * @description 分页查询秒杀控制
     */
    @GetMapping("/list")
    public JsonResult list(@RequestParam(name = "flag",required = false)Integer flag, @RequestParam(name = "startTime",required = false) Date startTime, @RequestParam(name = "endTime",required = false) Date endTime,
                           @RequestParam(name = "page",required = false)Integer page, @RequestParam(name = "size",required = false)Integer size){
        return success(smsGroupSaleService.queryPage(flag,startTime,endTime,page,size));
    }

    /**
     * @author liyuan
     * @description 删除拼团
     * @param ids
     * @return
     */
    @GetMapping("/delpl")
    public JsonResult delpl(@RequestParam(name = "ids",required = true ) String ids){
        smsGroupSaleService.delpl(ids);
        return success();
    }

}
