package com.langchao.waveservicemall.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.langchao.waveservicemall.pojo.OrderProduct;
import com.langchao.waveservicemall.pojo.vo.OrderDetailVo;
import com.langchao.waveservicemall.pojo.vo.ProductVo;

import java.util.List;

/**
 * @Title: OrderProductService
 * @ProjectName
 * @Description: TODO
 * @author 
 * @date Mon Feb 17 16:34:44 CST 2020
 */
public interface OrderProductService extends IService<OrderProduct> {

    /**
     * @Author liyuan
     * @Description 根据订单编号加载订单详情
     * @param orderNumber
     * @return
     */
    List<OrderDetailVo> getOrderDetailByOrderNumber(String orderNumber);

    /**
     * @Author liyuan
     * @Description 生产商品订单
     * @return
     */
    String addNewOrder(ProductVo productVo);

    /**
     * @Author liyuan
     * @Description 支付
     * @param orderNumber
     */
    void pay(String orderNumber);

}
