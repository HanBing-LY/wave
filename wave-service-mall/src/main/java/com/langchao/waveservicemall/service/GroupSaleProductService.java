package com.langchao.waveservicemall.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.langchao.waveservicemall.pojo.GroupSaleProduct;
import com.langchao.waveservicemall.pojo.ProductColumn;
import com.langchao.waveservicemall.pojo.ProductInfo;

import java.util.Date;
import java.util.List;

/**
 * @Title: GroupSaleProductService
 * @ProjectName
 * @Description: TODO
 * @author 
 * @date Mon Feb 17 16:34:44 CST 2020
 */
public interface GroupSaleProductService extends IService<GroupSaleProduct> {

    /**
     * @Author liyuan
     * @Description 分页查询
     */
   IPage list(Integer groupSaleId, String productName, Integer flag, Date startTime, Date endTime, Integer page, Integer size);

    /**
     * @Author liyuan
     * @Description 停止商品拼团
     */
    void delpl(String checkIds);

    /**
     * @Author liyuan
     * @Description 商品二级分类
     */
    List<ProductColumn> columns();

    /**
     * @Author liyuan
     * @Description 商品二级分类id查询商品
     */
    List<ProductInfo> columnsProducts(Integer columnId);

    /**
     * @Author liyuan
     * @Description 选择拼团商品
     * @return
     */
    void chooseProduct(GroupSaleProduct groupSaleProduct);

    /**
     * @Author liyuan
     * @Description 修改秒杀价
     * @return
     */
    void updatePrice(Integer groupSaleProductId, Double price);
}
