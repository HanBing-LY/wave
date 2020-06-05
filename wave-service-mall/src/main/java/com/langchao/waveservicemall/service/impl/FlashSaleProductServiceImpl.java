package com.langchao.waveservicemall.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.langchao.wavecommon.exception.CommonException;
import com.langchao.wavecommon.util.StringUtils;
import com.langchao.waveservicemall.constant.PageControlInformations;
import com.langchao.waveservicemall.mapper.FlashSaleProductMapper;
import com.langchao.waveservicemall.mapper.FlashSaleProductSkuMapper;
import com.langchao.waveservicemall.mapper.ProductColumnMapper;
import com.langchao.waveservicemall.mapper.ProductInfoMapper;
import com.langchao.waveservicemall.pojo.FlashSaleProduct;
import com.langchao.waveservicemall.pojo.FlashSaleProductSku;
import com.langchao.waveservicemall.pojo.ProductColumn;
import com.langchao.waveservicemall.pojo.ProductInfo;
import com.langchao.waveservicemall.service.FlashSaleProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @Title: FlashSaleProductServiceImpl
 * @ProjectName
 * @Description: TODO
 * @author 
 * @date Mon Feb 17 16:34:43 CST 2020
 */
@Service
@Transactional
public class FlashSaleProductServiceImpl extends ServiceImpl<FlashSaleProductMapper, FlashSaleProduct> implements FlashSaleProductService {
    @Autowired
    private FlashSaleProductMapper flashSaleProductMapper;
    @Autowired
    private FlashSaleProductSkuMapper flashSaleProductSkuMapper;
    @Autowired
    private ProductInfoMapper productInfoMapper;
    @Autowired
    private ProductColumnMapper productColumnMapper;

    /**
     * @author liyuan
     * @Description  分页查询所有秒杀控制
     * @param page
     * @param size
     * @return
     */
    @Override
    public IPage list(Integer flashSaleId, String productName, Integer flag, Date startTime, Date endTime, Integer page, Integer size) {
        if(StringUtils.isNull(page) || page < 1){
            page = PageControlInformations.DEFAULT_PAGE_NUMBER;
        }
        if(StringUtils.isNull(size) || size < 1 || size > PageControlInformations.MAX_PAGE_SIZE){
            size = PageControlInformations.FLASH_SALE_DEFAULT_PAGE_SIZE;
        }
        return flashSaleProductMapper.selectPage(new Page<FlashSaleProduct>(page,size),new QueryWrapper<FlashSaleProduct>());
    }

    /**
     * @author liyuan
     * @Description  停止商品秒杀
     * @return
     */
    @Override
    @Transactional(rollbackFor = CommonException.class)
    public void delpl(String checkIds) {
        if(StringUtils.isEmpty(checkIds)){
            // throw new CommonException("id为空");
        }
        List<Integer> flashSaleProductIds = StringUtils.stringToIntegerList(checkIds);
        QueryWrapper<FlashSaleProductSku> flashSaleProductSkuQueryWrapper = new QueryWrapper<>();
        flashSaleProductSkuQueryWrapper.in("flashSaleProductId",flashSaleProductIds);
        List<FlashSaleProductSku> flashSaleProductSkus = flashSaleProductSkuMapper.selectList(flashSaleProductSkuQueryWrapper);
        if(flashSaleProductSkus.size() != 0){
            // throw new CommonException("已有商品秒杀,禁止删除");
        }
        flashSaleProductMapper.deleteByProductSkuIds(flashSaleProductIds);
    }

    /**
     * @Author liyuan
     * @Description 商品二级分类
     */
    @Override
    public List<ProductColumn> columns() {
        QueryWrapper<ProductColumn> productColumnQueryWrapper = new QueryWrapper<>();
        productColumnQueryWrapper.isNotNull("parentId");
        List<ProductColumn> productColumns = productColumnMapper.selectList(productColumnQueryWrapper);
        return productColumns;
    }

    /**
     * @author liyuan
     * @param columnId
     * @return
     */
    @Override
    public List<ProductInfo> columnsProducts(Integer columnId) {
        QueryWrapper<ProductInfo> productInfoQueryWrapper = new QueryWrapper<>();
        productInfoQueryWrapper.eq("productColumId",columnId);
        List<ProductInfo> productInfos = productInfoMapper.selectList(productInfoQueryWrapper);
        return productInfos;
    }


    /**
     * @Author liyuan
     * @Description 选择秒杀商品
     * @param flashSaleProduct
     * @return
     */
    @Override
    public void chooseProduct(FlashSaleProduct flashSaleProduct) {
        if(StringUtils.isNull(flashSaleProduct.getProductId()) || StringUtils.isNull(flashSaleProduct.getFlashSaleId()) || StringUtils.isNull(flashSaleProduct.getPrice())){
            // throw new CommonException("数据丢失");
        }
        ProductInfo productInfo = productInfoMapper.selectById(flashSaleProduct.getProductId());
        if(flashSaleProduct.getFlashSaleId() > productInfo.getProductSalePrice()){
            // throw new CommonException("秒杀价必须小于售价");
        }
        flashSaleProductMapper.insert(flashSaleProduct);
    }

    /**
     * @Author liyuan
     * @Description 修改秒杀价
     * @return
     */
    @Override
    public void updatePrice(Integer flashSaleProductId, Double price) {
        if(StringUtils.isNull(price)){
            // throw new CommonException("价格丢失");
        }
        FlashSaleProduct flashSaleProduct = flashSaleProductMapper.selectById(flashSaleProductId);
        if(StringUtils.isNull(flashSaleProduct)){
            // throw new CommonException("数据丢失");
        }
        ProductInfo productInfo = productInfoMapper.selectById(flashSaleProduct.getProductId());
        if(flashSaleProduct.getFlashSaleId() > productInfo.getProductSalePrice()){
            // throw new CommonException("秒杀价必须小于售价");
        }
        flashSaleProduct.setPrice(price);
        flashSaleProductMapper.updateById(flashSaleProduct);
    }
}
