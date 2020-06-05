package com.langchao.waveservicemall.service.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.langchao.waveservicemall.mapper.OrderInfoMapper;
import com.langchao.waveservicemall.mapper.OrderProductMapper;
import com.langchao.waveservicemall.mapper.ProductInfoMapper;
import com.langchao.waveservicemall.pojo.*;
import com.langchao.waveservicemall.pojo.dto.OrderInfoDto;
import com.langchao.waveservicemall.pojo.vo.ScoreOrderInfoVo;
import com.langchao.waveservicemall.service.OrderInfoService;
import org.apache.lucene.spatial3d.geom.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author
 * @Title: OrderInfoServiceImpl
 * @ProjectName
 * @Description: TODO
 * @date Mon Feb 17 16:34:44 CST 2020
 */
@Service
@Transactional
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo> implements OrderInfoService {
    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Autowired
    private OrderProductMapper orderProductMapper;

    @Autowired
    private ProductInfoMapper productInfoMapper;


    /**
     * @param userId,productId
     * @return 订单编号
     * @description 生成订单信息(代付款)
     **/
    @Override
    public Map confirmOrderProduct(Integer userId, Integer productId) {
        //生成代付款订单对象
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setOrderType("1");
        orderInfo.setAddtime(new Date());
        orderInfo.setFareMoney(0.0);
        orderInfo.setUserId(userId);
        //创建订单编号
        String orderNumber = Tools.ordernumber();
        orderInfo.setOrderNumber(orderNumber);
        //查询用户默认收货地址，如没有则让用户选择然后在另一个接口绑定到订单里
        UserAddress userAddress = userAddressComsumerService.getDefaultAddress(userId);
        if (userAddress != null) {
            orderInfo.setProvince(userAddress.getProvince());
            orderInfo.setDistrict(userAddress.getDistrict());
            orderInfo.setArea(userAddress.getArea());
            orderInfo.setAddress(userAddress.getAddress());
            orderInfo.setReceiveName(userAddress.getUserName());
            orderInfo.setReceivePhone(userAddress.getUserPhone());
        }
        //获取商品金额等信息
        ProductInfo productInfo = productInfoMapper.selectByPrimaryKey(productId);
        orderInfo.setPayMoney(productInfo.getProductSalePrice());
        orderInfo.setPayScore(productInfo.getProductScore());
        //订单信息存入数据库
        OrderInfoMapper.insert(orderInfo);
        //将订单信息关联到orderproduct中
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setOrderId(orderInfo.getId());
        orderProduct.setProductNum(1);
        orderProduct.setProductId(productInfo.getId());
        orderProductMapper.insert(orderProduct);

        Map map = new HashMap();
        map.put("orderNumber", orderNumber);

        return map;
    }

    /**
     * @param orderNumber
     * @return
     * @description 根据ordernumber查询订单信息
     **/
    @Override
    public ScoreOrderInfoVo getScoreOrderInfo(String orderNumber) {
        //创建返回对象
        ScoreOrderInfoVo scoreOrderInfoVo = new ScoreOrderInfoVo();
        //查询订单信息
        Condition condition = new Condition(OrderInfo.class);
        Example.Criteria criteria = condition.createCriteria();
        criteria.andEqualTo("orderNumber", orderNumber);
        List<OrderInfo> orderInfoList = OrderInfoMapper.selectByCondition(condition);
        OrderInfo orderInfo = new OrderInfo();
        if (orderInfoList.size() > 0) {
            orderInfo = orderInfoList.get(0);
        }
        BeanUtils.copyProperties(orderInfo, scoreOrderInfoVo);
        //查询订单相关联的积分产品
        OrderProduct orderProduct = new OrderProduct();
        Condition orderProductCondition = new Condition(OrderProduct.class);
        Example.Criteria orderProductCriteria = orderProductCondition.createCriteria();
        //通过orderId查询到产品id
        orderProductCriteria.andEqualTo(orderInfo.getId());
        List<OrderProduct> productList = orderProductMapper.selectByCondition(orderProductCondition);
        if (productList.size() > 0) {
            orderProduct = productList.get(0);
        }
        //根据产品id查询产品信息
        ProductInfo productInfo = productInfoMapper.selectByPrimaryKey(orderProduct.getId());

        BeanUtils.copyProperties(productInfo, scoreOrderInfoVo);
        //0运费
        scoreOrderInfoVo.setFareMoney(0.0);

        return scoreOrderInfoVo;
    }

    /**
     * @param orderNumber,payMoney
     * @return
     * @author ws
     * @description 积分商品支付
     **/
    @Override
    public String payScoreOrder(String orderNumber,Double payMoney) {
        //根据orderNumber查询订单信息
        Condition condition = new Condition(OrderInfo.class);
        Example.Criteria criteria = condition.createCriteria();
        criteria.andEqualTo("orderNumber", orderNumber);
        List<OrderInfo> orderInfoList = OrderInfoMapper.selectByCondition(condition);
        OrderInfo orderInfo = new OrderInfo();
        if (orderInfoList.size() > 0) {
            orderInfo = orderInfoList.get(0);
            if(orderInfo.getOrderType()!="1"){
                return "该订单已支付";
            }
        }
        //查询用户积分信息
        UserInfo userInfo = userConsumerService.getUsers(orderInfo.getUserId());
        //判断用户积分是否足够 不足则直接返回错误信息
        if (userInfo.getUserScore() < orderInfo.getPayScore()) {
            return "您的积分不足";
        } else {
            //如果订单只需积分支付 则直接扣除积分，完结订单
            if (orderInfo.getPayMoney() <= 0 && orderInfo.getPayScore() > 0) {
                try {
                    //添加积分明细
                    ScoreRecord scoreRecord = new ScoreRecord(null, "购买积分商品", "-" + orderInfo.getPayScore(), new Date(), orderInfo.getUserId());
                    scoreRecordConsumerService.add(scoreRecord);
                    //扣除用户积分
                    OrderInfoMapper.reduceUserScore(orderInfo.getUserId(), orderInfo.getPayScore());
                    //更改订单状态
                    orderInfo.setOrderType("2");
                    //优惠券抵扣 设置payMoney
                    orderInfo.setPayMoney(payMoney);
                    //订单支付方式
                    orderInfo.setPayMethod("3");
                   /* orderInfo.setPayNumber("支付编号");*/
                    update(orderInfo);

                } catch (Exception e) {
                    return "商品兑换失败";
                }
                return "兑换成功";
                //如果订单为混合支付  等待微信支付返回结果再对订单状态进行更改
            }
            return "商品兑换失败";
        }
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
    @Override
    public IPage findManagerList(Integer page, Integer size, String orderNumber, String payNumber, String orderType, String payMethod, String receivePhone, String userName) {
        PageHelper.startPage(page, size);
        Map map = new HashMap();
        if (orderNumber != null) {
            map.put("orderNumber", orderNumber);
        }
        if (StringUtils.isNotEmpty(payNumber)) {
            map.put("payNumber", payNumber);
        }
        if (StringUtils.isNotEmpty(orderType)) {
            map.put("orderType", orderType);
        }
        if (StringUtils.isNotEmpty(payMethod)) {
            map.put("payMethod", payMethod);
        }
        if(StringUtils.isNotEmpty(receivePhone)){
            map.put("receivePhone",receivePhone);
        }
        if(StringUtils.isNotEmpty(userName)){
            map.put("userName",userName);
        }
        List<OrderInfoDto> managerList = OrderInfoMapper.findManagerList(map);
        for (OrderInfoDto orderInfoDto : managerList) {
            if (orderInfoDto.getUserId() != null) {
                orderInfoDto.setUserName(userConsumerService.getUsers(orderInfoDto.getUserId()).getUserName());
            }
        }
       IPage<OrderInfoDto> pageInfo = newIPage<>(managerList);
        return pageInfo;
    }

    /**
     * @Description 修改付款状态为已发货，并且添加物流单号
     * @Author Renjinliang
     * @date 2020/3/26 15:31
     * 1:失败 0：成功
     */
    @Override
    public Integer updateLogisticsNum(Integer id, String logisticsNum) {
        OrderInfo orderInfo = OrderInfoMapper.selectByPrimaryKey(id);
        if ("4".equals(orderInfo.getOrderType())) {
            return 1;
        } else {
            orderInfo.setOrderType("3");
            orderInfo.setLogisticsNum(logisticsNum);
            update(orderInfo);
            return 0;
        }

    }


}


