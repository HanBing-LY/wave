package com.liyuan.wave.sms.controller;

import com.liyuan.wave.common.vo.response.JsonResult;
import com.liyuan.wave.common.web.BaseController;
import com.liyuan.wave.sms.po.vo.SmsFlashSaleSaveVo;
import com.liyuan.wave.sms.service.SmsFlashSaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author liyuan
 * @description sms_flash_sale
 * @email 724837404@qq.com
 * @date 2020-06-15 16:09:35
 */
@RestController
@RequestMapping
public class SmsFlashSaleController extends BaseController {

    @Autowired
    private SmsFlashSaleService smsFlashSaleService;

    /**
     * @param smsFlashSaleSaveVo
     * @author liyuan
     * @description 添加秒杀控制
     */
    @PostMapping
    public JsonResult add(@RequestBody SmsFlashSaleSaveVo smsFlashSaleSaveVo) {
        smsFlashSaleService.addFlashSale(smsFlashSaleSaveVo);
        return success();
    }

    /**
     * @param smsFlashSaleSaveVo
     * @author liyuan
     * @description 修改秒杀控制
     */
    @PutMapping
    public JsonResult update(@RequestBody SmsFlashSaleSaveVo smsFlashSaleSaveVo) {
        smsFlashSaleService.updateFlashSale(smsFlashSaleSaveVo);
        return success();
    }

    /**
     * @author liyuan
     * @description 分页查询秒杀控制
     */
    @GetMapping("/list")
    public JsonResult list(@RequestParam(name = "status", required = false) Byte status,
                           @RequestParam(name = "page", required = false) Long page, @RequestParam(name = "size", required = false) Long size) {
        return success(smsFlashSaleService.queryPage(status, page, size));
    }

    /**
     * @param ids
     * @return
     * @author liyuan
     * @description 删除秒杀
     */
    @GetMapping("/stop")
    public JsonResult stop(@RequestParam(name = "ids", required = true) String ids) {
        smsFlashSaleService.disable(ids);
        return success();
    }

}
