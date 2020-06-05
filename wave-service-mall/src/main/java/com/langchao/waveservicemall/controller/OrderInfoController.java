package com.langchao.waveservicemall.controller;

import com.chemguan.business.results.JsonResult;
import com.chemguan.business.results.JsonResultGenerator;
import com.chemguan.entity.OrderInfo;
import com.chemguan.service.OrderInfoService;
import com.chemguan.service.OrderProductService;
import com.chemguan.vo.ProductVo;
import com.langchao.wavecommon.web.BaseController;
import org.apache.commons.lang.StringUtils;
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
     * @param userAddressId
     * @return
     * @Author liyuan
     * @Description 选择收货地址
     */
    @PutMapping("/chooseReceiveAddress")
    public JsonResult chooseReceiveAddress(@RequestParam(name = "orderNumber", required = true) String orderNumber, @RequestParam(name = "userAddressId", required = true) Integer userAddressId) {
        orderProductService.chooseReceiveAddress(orderNumber, userAddressId);
        return success();
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

    /**
     * @param productId 积分商品id
     * @return
     * @author ws
     * @description 确认订单 点击立即兑换跳转到此页面
     **/
    @GetMapping("/confirmScoreProductOrder")
    public JsonResult confirmOrderProduct(@RequestParam(required = true) Integer userId,
                                      @RequestParam(required = true) Integer productId) {
        return success(orderInfoService.confirmOrderProduct(userId, productId));
    }

    /**
     * @param orderNumber
     * @return
     * @author ws
     * @description 查询订单详情
     **/
    @GetMapping("/getScoreOrderInfo")
    public JsonResult getScoreOrderInfo(@RequestParam(required = true) String orderNumber) {

        return success(orderInfoService.getScoreOrderInfo(orderNumber));
    }


    /**
     * @param orderNumber
     * @return
     * @author ws
     * @description 积分商品订单付款
     **/
    @PostMapping("/payScoreOrder")
    public JsonResult payOrderByScoreAndOtherType(@RequestParam(required = true) String orderNumber,
                                              @RequestParam(required = true) Double payMoney) {
        String msg = orderInfoService.payScoreOrder(orderNumber,payMoney);
        if(msg=="兑换成功"){
            return success(msg);
        }else{
            return JsonResultGenerator.genFailJsonResult(msg);
        }
    }




    /**
     * @Description 后台添加
     * @Author Renjinliang
     * @date 2020/3/23 11:21
     */
    @PostMapping
    public JsonResult add(@RequestBody OrderInfo orderInfo) {
        orderInfoService.save(orderInfo);
        return success();
    }

    /**
     * 后台批量删除
     *
     * @param checkIds
     * @return
     */
    @GetMapping("delpl")
    public JsonResult delpl(@RequestParam("checkIds") String checkIds) {
        if (StringUtils.isEmpty(checkIds)) {
            return JsonResultGenerator.genFailJsonResult("数据为空!");
        }
        try {
            orderInfoService.deleteByIds(checkIds);
        } catch (Exception e) {
            return JsonResultGenerator.genFailJsonResult("所选数据已被关联无法删除");
        }
        return success();
    }

    /**
     * @Description 后台修改
     * @Author Renjinliang
     * @date 2020/3/23 11:22
     */
    @PutMapping("/update")
    public JsonResult update(@RequestBody OrderInfo orderInfo) {
        orderInfoService.update(orderInfo);
        return success();
    }

    /**
     * @Description 后台详情
     * @Author Renjinliang
     * @date 2020/3/23 11:22
     */
    @GetMapping("/{id}")
    public JsonResult detail(@PathVariable Integer id) {
        return success(orderInfoService.findById(id));
    }

    /**
     * @param orderNumber 订单编号
     * @param payNumber   付订单编号
     * @param orderType   1:待付款 2:待发货 3:已发货 4:已退款
     * @param payMethod   支付方式1:微信 2:银联 3:积分
     * @Description 后台list
     * @Author Renjinliang
     * @date 2020/3/26 13:30
     */
    @GetMapping
    public JsonResult list(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size,
            @RequestParam(name = "orderNumber", defaultValue = "") String orderNumber,
            @RequestParam(name = "payNumber", defaultValue = "") String payNumber,
            @RequestParam(name = "orderType", defaultValue = "") String orderType,
            @RequestParam(name = "payMethod", defaultValue = "") String payMethod,
            @RequestParam(name = "receivePhone", defaultValue = "") String receivePhone,
            @RequestParam(name = "userName", defaultValue = "") String userName) {
        return success(orderInfoService.findManagerList(page, size, orderNumber, payNumber, orderType, payMethod,receivePhone,userName));
    }

    /**
     * @Description 修改付款状态为已发货，并且添加物流单号
     * @Author Renjinliang
     * @date 2020/3/26 15:31
     * * 1:失败 0：成功
     */
    @PostMapping("updateLogisticsNum")
    public JsonResult updateLogisticsNum(@RequestParam(name = "id", defaultValue = "") Integer id,
                                     @RequestParam(name = "logisticsNum", defaultValue = "") String logisticsNum) {
        Integer integer = orderInfoService.updateLogisticsNum(id, logisticsNum);
        if (integer == 1) {
            return JsonResultGenerator.genFailJsonResult("该订单已退款");
        } else {
            return success();
        }
    }

}
