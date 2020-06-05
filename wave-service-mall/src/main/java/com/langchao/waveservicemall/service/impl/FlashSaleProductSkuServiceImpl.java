package com.langchao.waveservicemall.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.langchao.wavecommon.exception.CommonException;
import com.langchao.wavecommon.util.StringUtils;
import com.langchao.waveservicemall.constant.PageControlInformations;
import com.langchao.waveservicemall.mapper.*;
import com.langchao.waveservicemall.pojo.*;
import com.langchao.waveservicemall.pojo.dto.ColumnNatureDTO;
import com.langchao.waveservicemall.pojo.vo.FlashSaleProductSkuVo;
import com.langchao.waveservicemall.service.FlashSaleProductSkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Title: FlashSaleProductSkuServiceImpl
 * @ProjectName
 * @Description: TODO
 * @author 
 * @date Mon Feb 17 16:34:43 CST 2020
 */
@Service
@Transactional
public class FlashSaleProductSkuServiceImpl extends ServiceImpl<FlashSaleProductSkuMapper, FlashSaleProductSku> implements FlashSaleProductSkuService {

    @Autowired
    private FlashSaleProductMapper flashSaleProductMapper;
    @Autowired
    private FlashSaleProductSkuMapper flashSaleProductSkuMapper;
    @Autowired
    private ProductInfoMapper productInfoMapper;
    @Autowired
    private ProductSkuMapper productSkuMapper;
    @Autowired
    private ColumnNatureMapper columnNatureMapper;
    @Autowired
    private GroupSaleProductSkuMapper groupSaleProductSkuMapper;
    @Autowired
    private ProductSkuNatureMapper productSkuNatureMapper;

    /**
     * @author liyuan
     * @Description  分页查询所有秒杀控制
     * @param page
     * @param size
     * @return
     */
    @Override
    public IPage list(Integer flashSaleProductId, Integer page, Integer size) {
        if (StringUtils.isNull(page) || page < 1) {
            page = PageControlInformations.DEFAULT_PAGE_NUMBER;
        }
        if (StringUtils.isNull(size) || size < 1 || size > PageControlInformations.MAX_PAGE_SIZE) {
            size = PageControlInformations.FLASH_SALE_DEFAULT_PAGE_SIZE;
        }
        return flashSaleProductSkuMapper.selectPage(new Page<FlashSaleProductSku>(page,size),new QueryWrapper<FlashSaleProductSku>());
    }

    /**
     * @Author liyuan
     * @Description 分页查询秒杀控制
     * @param flashSaleProductId
     * @return
     */
    @Override
    public List<ColumnNatureDTO> listGetNatureToChoose(Integer flashSaleProductId) {
        FlashSaleProduct flashSaleProduct = flashSaleProductMapper.selectById(flashSaleProductId);
        if(StringUtils.isNull(flashSaleProduct)){
            // throw new CommonException("数据为空");
        }
        List<ColumnNatureDTO> columnNatureDTOS=productSkuNatureMapper.listGetNatureToChoose(flashSaleProduct.getProductId());
        return columnNatureDTOS;
    }

    @Override
    public void delpl(String checkIds) {
        if(StringUtils.isEmpty(checkIds)){
            // throw new CommonException("数据丢失");
        }
        List<Integer> flashSaleProductIds = StringUtils.stringToIntegerList(checkIds);
        flashSaleProductSkuMapper.deleteByProductSkuIds(flashSaleProductIds);
    }

    @Override
    public void updatePrice(Integer flashSaleProductSkuId, Double price) {
        FlashSaleProductSku flashSaleProductSku = flashSaleProductSkuMapper.selectById(flashSaleProductSkuId);
        if(StringUtils.isNull(flashSaleProductSku)){
            // throw new CommonException("数据丢失");
        }
        if(price < flashSaleProductSku.getPrice()){
            // throw new CommonException("商品秒杀价不得低于最低秒杀价");
        }
        flashSaleProductSku.setPrice(price);
        flashSaleProductSkuMapper.updateById(flashSaleProductSku);

    }

    @Override
    @Transactional(rollbackFor = CommonException.class)
    public void chooseProduct(FlashSaleProductSkuVo flashSaleProductSkuVo) {
        List<Integer> natureIds = StringUtils.stringToIntegerList(flashSaleProductSkuVo.getNatureId());
        List<Integer> natureValueIds = StringUtils.stringToIntegerList(flashSaleProductSkuVo.getNatureValueId());
        if(natureIds.size() != natureValueIds.size() || natureIds.size() < 1 ){
            // throw new CommonException("属性 与属性值 不匹配");
        }
        FlashSaleProduct flashSaleProduct = flashSaleProductMapper.selectById(flashSaleProductSkuVo.getFlashSaleProductId());
        QueryWrapper<ProductSku> productSkuQueryWrapper = new QueryWrapper<>();
        productSkuQueryWrapper.eq("productId",flashSaleProduct.getProductId());
        List<ProductSku> productSkus = productSkuMapper.selectList(productSkuQueryWrapper);
        List<ProductSkuNature> productSkuNatures = productSkuNatureMapper.findByProductSkuIdList(productSkus.stream().map(ProductSku::getId).distinct().collect(Collectors.toList()));
        List<Integer> productSkuIds = new ArrayList<>();
        for (ProductSkuNature psn:productSkuNatures ) {
            for(int a= 0;a<natureIds.size();a++){
                if(psn.getColumnNatureId().equals(natureIds.get(a)) && psn.getNatureValueId().equals(natureValueIds.get(a))){
                    productSkuIds.add( psn.getProductSkuId());
                }
            }
        }
        if(productSkuIds.size()!=1){
            // throw new CommonException("商品属性丢失,请重选");
        }
        QueryWrapper<GroupSaleProductSku> groupSaleProductSkuQueryWrapper = new QueryWrapper<>();
        groupSaleProductSkuQueryWrapper.eq("productId",productSkuIds.get(0));
        List<GroupSaleProductSku> groupSaleProductSkus = groupSaleProductSkuMapper.selectList(groupSaleProductSkuQueryWrapper);
        if(groupSaleProductSkus.size() > 0 ){
            // throw new CommonException("改规格商品已有拼团记录");
        }
        if(flashSaleProductSkuVo.getFlashSalePrice() < flashSaleProduct.getPrice()){
            // throw new CommonException("商品秒杀价不得低于最低秒杀价");
        }
        FlashSaleProductSku flashSaleProductSku = new FlashSaleProductSku(null,flashSaleProduct.getFlashSaleId(),productSkuIds.get(0),flashSaleProductSkuVo.getFlashSalePrice(),flashSaleProduct.getId());
        flashSaleProductSkuMapper.insert(flashSaleProductSku);
    }
}
