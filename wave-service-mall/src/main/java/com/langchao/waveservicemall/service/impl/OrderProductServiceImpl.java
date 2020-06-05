package com.langchao.waveservicemall.service.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.gson.Gson;
import com.langchao.wavecommon.exception.CommonException;
import com.langchao.waveservicemall.mapper.*;
import com.langchao.waveservicemall.pojo.*;
import com.langchao.waveservicemall.pojo.dto.OrderProductDto;
import com.langchao.waveservicemall.pojo.vo.OrderDetailVo;
import com.langchao.waveservicemall.pojo.vo.ProductVo;
import com.langchao.waveservicemall.service.OrderProductService;
import org.apache.lucene.spatial3d.geom.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Title: OrderProductServiceImpl
 * @ProjectName
 * @Description: TODO
 * @author 
 * @date Mon Feb 17 16:34:44 CST 2020
 */
@Service
public class OrderProductServiceImpl extends ServiceImpl<OrderProductMapper, OrderProduct> implements OrderProductService {
    @Autowired
    private OrderInfoMapper orderInfoMapper;
    @Autowired
    private OrderProductMapper orderProductMapper;
    @Autowired
    private ProductInfoMapper productInfoMapper;
    @Autowired
    private ProductSkuMapper productSkuMapper;
    @Autowired
    private ProductSkuNatureMapper productSkuNatureMapper;
    @Autowired
    private ColumnNatureMapper columnNatureMapper;
    @Autowired
    private NatureValueMapper natureValueMapper;

    /**
     * @Author liyuan
     * @Description 根据订单编号加载订单详情
     * @param orderNumber
     * @return
     */
    @Override
    public List<OrderDetailVo> getOrderDetailByOrderNumber(String orderNumber) {
        return orderProductMapper.getOrderDetailByOrderNumber(orderNumber);
    }

    @Override
    public String addNewOrder(ProductVo productVo) {
        if(productVo == null ){
            // throw new CommonException("数据丢失");
        }
        String orderNumber = Tools.randomcode(18);
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setOrderNumber(orderNumber);
        orderInfo.setOrderMoney(productVo.getProductPrice()*productVo.getProductNum());
        orderInfo.setUserId(productVo.getUserId());
        orderInfo.setAddtime(new Date());
        orderInfo.setOrderType("1");
//        查询用户收获地址
        Result result = userAddressComsumerService.listGetByUserId(productVo.getUserId());
        List<UserAddress> userAddressList = new Gson().fromJson(new Gson().toJson(result.getData()), new TypeToken<List<UserAddress>>(){}.getType());
//        如果有默认地址则赋值
        for(UserAddress userAddress:userAddressList){
            if(userAddress.getFlag() == 1){
                orderInfo.setReceiveName(userAddress.getUserName());
                orderInfo.setReceivePhone(userAddress.getUserPhone());
                orderInfo.setProvince(userAddress.getProvince());
                orderInfo.setDistrict(userAddress.getDistrict());
                orderInfo.setArea(userAddress.getArea());
                orderInfo.setAddress(userAddress.getAddress());
            }
        }
        orderInfoMapper.insert(orderInfo);
        List<Integer> natureIds = StringUtils.StringToIntegerList(productVo.getNatureId());
        List<Integer> natureValueIds = StringUtils.StringToIntegerList(productVo.getNatureValueId());
        if(natureIds.size() != natureValueIds.size() || natureIds.size() < 1 ){
            // throw new CommonException("属性 与属性值 不匹配");
        }
//        查询唯一productsky
        Condition condition = new Condition(ProductSku.class);
        Example.Criteria criteria = condition.createCriteria();
        criteria.andEqualTo("productId",productVo.getProductId());
        List<ProductSku> productSkus = productSkuMapper.selectByCondition(condition);
        List<ProductSkuNature> productSkuNatures = productSkuNatureMapper.findByProductSkuIdList(productSkus.stream().map(i -> i.getId()).distinct().collect(Collectors.toList()));
        List<Integer> productSkuIds = new ArrayList<>();
        for (ProductSkuNature psn:productSkuNatures ) {
            for(int a= 0;a<natureIds.size();a++){
                if(psn.getColumnNatureId().equals(natureIds.get(a)) && psn.getNatureValueId().equals(natureValueIds.get(a))){
                    productSkuIds.add( psn.getProductSkuId());
                }
            }
        }
        productSkuIds = productSkuIds.stream().distinct().collect(Collectors.toList());
        if(productSkuIds.size()!=1){
            // throw new CommonException("商品属性丢失");
        }
        OrderProduct orderProduct = new OrderProduct(null,orderInfo.getId(),productSkuIds.get(0),productVo.getProductNum(),productVo.getProductNum()*productVo.getProductPrice(),productVo.getProductId());
        orderProductMapper.insert(orderProduct);
        return orderNumber;
    }

    /**
     * @Author liyuan
     * @Description 支付
     * @param orderNumber
     */
    @Override
    public void pay(String orderNumber) {
//        调用第三方支付接口
//        修改订单信息
        Condition orderInfoCondition = new Condition(OrderInfo.class);
        Example.Criteria orderInfoCriteria = orderInfoCondition.createCriteria();
        orderInfoCriteria.andEqualTo("orderNumber",orderNumber);
        List<OrderInfo> orderInfos = orderInfoMapper.selectByCondition(orderInfoCondition);
        if(orderInfos.size() != 1){
            // throw new CommonException("订单不存在");
        }
        OrderInfo orderInfo = orderInfos.get(0);

        Condition orderCondition = new Condition(OrderProduct.class);
        Example.Criteria orderCriteria = orderCondition.createCriteria();
        orderCriteria.andEqualTo("orderId",orderInfo.getId());
        List<OrderProduct> orderProducts = orderProductMapper.selectByCondition(orderCondition);
        if(orderProducts.size() !=1){

        }
//      TODO 赋值支付编号,实付金额,支付方式
        orderInfo.setOrderType("2");
        orderInfoMapper.updateByPrimaryKey(orderInfo);

        for(OrderProduct orderProduct:orderProducts){
//        productInfo加销量
            productInfoMapper.updateSaleNum(orderProduct.getProductId(),orderProduct.getProductNum());
//        productsku减库存
            ProductSku productSku = productSkuMapper.selectByPrimaryKey(orderProduct.getProductSkuId());
            productSkuMapper.updateStock(productSku.getId(),orderProduct.getProductNum());
        }
    }

}
