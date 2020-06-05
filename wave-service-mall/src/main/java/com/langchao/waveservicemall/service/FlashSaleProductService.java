package com.langchao.waveservicemall.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.langchao.waveservicemall.pojo.FlashSaleProduct;
import com.langchao.waveservicemall.pojo.ProductColumn;
import com.langchao.waveservicemall.pojo.ProductInfo;

import java.util.Date;
import java.util.List;

/**
 * @Title: FlashSaleProductService
 * @ProjectName
 * @Description: TODO
 * @author 
 * @date Mon Feb 17 16:34:43 CST 2020
 */
public interface FlashSaleProductService   {

    /**
     * @author liyuan
     * @Description  分页查询所有秒杀控制
     * @param page
     * @param size
     * @return
     */
   IPage list(Integer flashSaleId, String productName, Integer flag, Date startTime, Date endTime, Integer page, Integer size);

    /**
     * @author liyuan
     * @Description  停止商品秒杀
     * @return
     */
    void delpl(String checkIds);

    /**
     * @author liyuan
     * @Description 商品二级分类
     */
    List<ProductColumn> columns();

    /**
     * @author liyuan
     * @param columnId
     * @return
     */
    List<ProductInfo> columnsProducts(Integer columnId);

    /**
     * @Author liyuan
     * @Description 选择秒杀商品
     * @param flashSaleProduct
     * @return
     */
    void chooseProduct(FlashSaleProduct flashSaleProduct);

    /**
     * @Author liyuan
     * @Description 修改秒杀价
     * @return
     */
    void updatePrice(Integer flashSaleProductId, Double price);
}
