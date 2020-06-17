package com.liyuan.wave.sms.controller;

import com.liyuan.wave.po.sms.SmsFlashSale;
import com.liyuan.wave.sms.service.SmsFlashSaleService;
import com.liyuan.wavecommon.vo.response.JsonResult;
import com.liyuan.wavecommon.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


/**
 * @description sms_flash_sale
 *
 * @author liyuan
 * @email 724837404@qq.com
 * @date 2020-06-15 16:09:35
 */
@RestController
@RequestMapping
public class SmsFlashSaleController extends BaseController {

    @Autowired
    private SmsFlashSaleService smsFlashSaleService;

    /**
     * @author liyuan
     * @description 添加秒杀控制
     * @param smsFlashSale
     */
    @PostMapping
    public JsonResult add(@RequestBody SmsFlashSale smsFlashSale){
        smsFlashSaleService.addFlashSale(smsFlashSale);
        return success();
    }

    /**
     * @author liyuan
     * @description 修改秒杀控制
     * @param smsFlashSale
     */
    @PutMapping
    public JsonResult update(@RequestBody SmsFlashSale smsFlashSale){
        smsFlashSaleService.updateFlashSale(smsFlashSale);
        return success();
    }

    /**
     * @author liyuan
     * @description 分页查询秒杀控制
     */
    @GetMapping("/list")
    public JsonResult list(@RequestParam(name = "flag",required = false)Integer flag, @RequestParam(name = "startTime",required = false) Date startTime, @RequestParam(name = "endTime",required = false) Date endTime,
                       @RequestParam(name = "page",required = false)Integer page, @RequestParam(name = "size",required = false)Integer size){
        return success(smsFlashSaleService.queryPage(flag,startTime,endTime,page,size));
    }

    /**
     * @author liyuan
     * @description 删除秒杀
     * @param ids
     * @return
     */
    @GetMapping("/delpl")
    public JsonResult delpl(@RequestParam(name = "ids",required = true ) String ids){
        smsFlashSaleService.delpl(ids);
        return success();
    }

}
