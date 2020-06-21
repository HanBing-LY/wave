package com.liyuan.wave.oms.controller;

import com.liyuan.wave.common.vo.response.JsonResult;
import com.liyuan.wave.common.web.BaseController;
import com.liyuan.wave.oms.service.OmsOrderInfoService;
import com.liyuan.wave.po.oms.vo.OmsOrderInfoSaveVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author liyuan
 * @description oms_order_info
 * @email 724837404@qq.com
 * @date 2020-06-15 16:11:12
 */
@RestController
@RequestMapping("/oms/orderinfo")
public class OmsOrderInfoController extends BaseController {

    @Autowired
    private OmsOrderInfoService omsOrderInfoService;

    /**
     * @param orderNumber
     * @return
     * @description 根据订单编号加载订单详情
     */
    @GetMapping("/detail")
    public JsonResult getOrderDetailByOrderNumber(@RequestParam(name = "orderNumber") String orderNumber) {
        return success(omsOrderInfoService.getOrderDetailByOrderNumber(orderNumber));
    }

    /**
     * @param userAddressId
     * @param orderNumber
     * @return
     * @description 选择收货地址
     */
    @PutMapping("/chooseReceiveAddress")
    public JsonResult chooseReceiveAddress(@RequestParam(name = "orderNumber") String orderNumber, @RequestParam(name = "userAddressId") Integer userAddressId) {
        omsOrderInfoService.chooseReceiveAddress(orderNumber, userAddressId);
        return success();
    }

    /**
     * @param omsOrderInfoSaveVo
     * @return
     * @description 确认商品订单
     */
    @PostMapping("/create")
    public JsonResult create(@RequestBody OmsOrderInfoSaveVo omsOrderInfoSaveVo) {
        return success(omsOrderInfoService.create(omsOrderInfoSaveVo));
    }

    /**
     * @param orderNumber
     * @return
     * @description 支付
     */
    @PostMapping("/pay")
    public JsonResult pay(@RequestParam(name = "orderNumber") String orderNumber) {
        omsOrderInfoService.pay(orderNumber);
        return success();
    }

}
