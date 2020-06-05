package com.langchao.waveservicemall.controller;

import com.langchao.wavecommon.vo.response.JsonResult;
import com.langchao.wavecommon.web.BaseController;
import com.langchao.waveservicemall.pojo.vo.ProductVo;
import com.langchao.waveservicemall.service.OrderInfoService;
import com.langchao.waveservicemall.service.OrderProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author liyuan
 * @create 2020-03-20-19:57-周五
 */
@RestController
@RequestMapping("/orders")
public class OrderInfoController  extends BaseController {

    @Autowired
    private OrderInfoService orderInfoService;
    @Autowired
    private OrderProductService orderProductService;

    /**
     * @param orderNumber
     * @return
     * @Author liyuan
     * @Description 根据订单编号加载订单详情
     */
    @GetMapping("/getOrderDetailByOrderNumber")
    public JsonResult getOrderDetailByOrderNumber(@RequestParam(name = "orderNumber", required = true) String orderNumber) {
        return success(orderProductService.getOrderDetailByOrderNumber(orderNumber));
    }

    /**
     * @return
     * @Author liyuan
     * @Description 生产商品订单
     */
    @PostMapping("/addNewOrder")
    public JsonResult addNewOrder(@RequestBody ProductVo productVo) {
        return success(orderProductService.addNewOrder(productVo));
    }

    /**
     * @return
     * @Author liyuan
     * @Description 支付
     */
    @PostMapping("/pay")
    public JsonResult pay(@RequestParam(name = "orderNumber", required = true) String orderNumber) {
        orderProductService.pay(orderNumber);
        return success();
    }

}
