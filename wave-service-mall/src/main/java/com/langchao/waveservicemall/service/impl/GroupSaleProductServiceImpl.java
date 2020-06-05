package com.langchao.waveservicemall.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.langchao.wavecommon.util.StringUtils;
import com.langchao.waveservicemall.constant.PageControlInformations;
import com.langchao.waveservicemall.mapper.GroupSaleProductMapper;
import com.langchao.waveservicemall.mapper.ProductColumnMapper;
import com.langchao.waveservicemall.mapper.ProductInfoMapper;
import com.langchao.waveservicemall.pojo.GroupSaleProduct;
import com.langchao.waveservicemall.pojo.ProductColumn;
import com.langchao.waveservicemall.pojo.ProductInfo;
import com.langchao.waveservicemall.pojo.vo.GroupSaleVo;
import com.langchao.waveservicemall.service.GroupSaleProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @Title: GroupSaleProductServiceImpl
 * @ProjectName
 * @Description: TODO
 * @author 
 * @date Mon Feb 17 16:34:44 CST 2020
 */
@Service
@Transactional
public class GroupSaleProductServiceImpl extends ServiceImpl<GroupSaleProductMapper,GroupSaleProduct> implements GroupSaleProductService {
    @Autowired
    private GroupSaleProductMapper groupSaleProductMapper;
    @Autowired
    private ProductInfoMapper productInfoMapper;
    @Autowired
    private ProductColumnMapper productColumnMapper;

    /**
     * @author liyuan
     * @Description 分页查询
     */
    @Override
    public IPage list(Integer groupSaleId, String productName, Integer flag, Date startTime, Date endTime, Integer page, Integer size) {
        if(StringUtils.isNull(page) || page < 1){
            page = PageControlInformations.DEFAULT_PAGE_NUMBER;
        }
        if(StringUtils.isNull(size) || size < 1 || size > PageControlInformations.MAX_PAGE_SIZE){
            size = PageControlInformations.FLASH_SALE_DEFAULT_PAGE_SIZE;
        }
        List<GroupSaleVo> flashSales = groupSaleProductMapper.list(groupSaleId,productName,flag,startTime,endTime);
        return groupSaleProductMapper.selectPage(new Page<GroupSaleProduct>(page,size),new QueryWrapper<GroupSaleProduct>());
    }

    /**
     * @author liyuan
     * @Description 停止商品
     */
    @Override
    public void delpl(String checkIds) {
        if(StringUtils.isEmpty(checkIds)){
            //  TODO id为空
            return;
        }
        List<Integer> groupSaleProductIds = StringUtils.stringToIntegerList(checkIds);
        QueryWrapper<GroupSaleProduct> groupSaleProductQueryWrapper = new QueryWrapper<>();
        groupSaleProductQueryWrapper.in("groupSaleProductId",groupSaleProductIds);
        List<GroupSaleProduct> groupSaleProductList = groupSaleProductMapper.selectList(groupSaleProductQueryWrapper);
        if(groupSaleProductList.size() != 0){
            // throw new CommonException("已有拼团秒杀,禁止删除");
        }
        groupSaleProductMapper.deleteByProductSkuIds(groupSaleProductIds);
    }

    /**
     * @author liyuan
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
     * @Description 商品二级分类id查询商品
     */
    @Override
    public List<ProductInfo> columnsProducts(Integer columnId) {
        QueryWrapper<ProductInfo> productInfoQueryWrapper = new QueryWrapper<>();
        productInfoQueryWrapper.eq("productColumId",columnId);
        List<ProductInfo> productInfos = productInfoMapper.selectList(productInfoQueryWrapper);
        return productInfos;
    }

    /**
     * @author liyuan
     * @Description 选择拼团商品
     * @param groupSaleProduct
     * @return
     */
    @Override
    public void chooseProduct(GroupSaleProduct groupSaleProduct) {
        if(StringUtils.isNull(groupSaleProduct.getProductId()) || StringUtils.isNull(groupSaleProduct.getGroupPeople()) || StringUtils.isNull(groupSaleProduct.getPrice()) || StringUtils.isNull(groupSaleProduct.getSuperType())|| StringUtils.isNull(groupSaleProduct.getGroupSaleId())){
            // TODO 数据丢失
        }
        ProductInfo productInfo = productInfoMapper.selectById(groupSaleProduct.getProductId());
        if(groupSaleProduct.getPrice() > productInfo.getProductSalePrice()){
            // throw new CommonException("拼团价必须小于售价");
        }
        groupSaleProductMapper.insert(groupSaleProduct);
    }

    @Override
    public void updatePrice(Integer groupSaleProductId, Double price) {
        if(StringUtils.isNull(price)){
            // TODO 价格丢失
        }
        GroupSaleProduct groupSaleProduct = groupSaleProductMapper.selectById(groupSaleProductId);
        if(StringUtils.isNull(groupSaleProduct)){
            // throw new CommonException("数据丢失");
        }
        ProductInfo productInfo = productInfoMapper.selectById(groupSaleProduct.getProductId());
        if(groupSaleProduct.getPrice() > productInfo.getProductSalePrice()){
            // throw new CommonException("拼团价必须小于售价");
        }
        groupSaleProduct.setPrice(price);
        groupSaleProductMapper.updateById(groupSaleProduct);

    }
}
