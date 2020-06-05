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
import com.langchao.waveservicemall.pojo.vo.GroupSaleProductSkuVo;
import com.langchao.waveservicemall.service.GroupSaleProductSkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Title: GroupSaleProductSkuServiceImpl
 * @ProjectName
 * @Description: TODO
 * @author 
 * @date Mon Feb 17 17:10:28 CST 2020
 */
@Service
@Transactional
public class GroupSaleProductSkuServiceImpl extends ServiceImpl<GroupSaleProductSkuMapper,GroupSaleProductSku> implements GroupSaleProductSkuService {

    @Autowired
    private GroupSaleProductMapper groupSaleProductMapper;
    @Autowired
    private GroupSaleProductSkuMapper groupSaleProductSkuMapper;
    @Autowired
    private ProductSkuMapper productSkuMapper;
    @Autowired
    private ProductSkuNatureMapper productSkuNatureMapper;
    @Autowired
    private FlashSaleProductSkuMapper flashSaleProductSkuMapper;

    @Override
    public IPage list(Integer groupSaleProductId, Integer page, Integer size) {
        if (StringUtils.isNull(page) || page < 1) {
            page = PageControlInformations.DEFAULT_PAGE_NUMBER;
        }
        if (StringUtils.isNull(size) || size < 1 || size > PageControlInformations.MAX_PAGE_SIZE) {
            size = PageControlInformations.FLASH_SALE_DEFAULT_PAGE_SIZE;
        }
        IPage<GroupSaleProductSku> ipage = new Page<>(page, size);
        List<GroupSaleProductSkuVo> flashSales = groupSaleProductSkuMapper.list(groupSaleProductId);
        QueryWrapper<GroupSaleProductSku> groupSaleProductSkuQueryWrapper = new QueryWrapper<>();
        groupSaleProductSkuQueryWrapper.eq("groupSaleProductId",groupSaleProductId);
        return groupSaleProductSkuMapper.selectPage(ipage,groupSaleProductSkuQueryWrapper);
    }

    @Override
    public List<ColumnNatureDTO> listGetNatureToChoose(Integer groupSaleProductId) {
        GroupSaleProduct groupSaleProduct = groupSaleProductMapper.selectById(groupSaleProductId);
        if(StringUtils.isNull(groupSaleProduct)){
            // throw new CommonException("数据丢失");
        }
        List<ColumnNatureDTO> columnNatureDTOS=productSkuNatureMapper.listGetNatureToChoose(groupSaleProduct.getProductId());
        return columnNatureDTOS;
    }

    @Override
    public void delpl(String checkIds) {
        if(StringUtils.isEmpty(checkIds)){
            // throw new CommonException("数据丢失");
        }
        List<Integer> groupSaleProductSkuIds = StringUtils.stringToIntegerList(checkIds);
        groupSaleProductSkuMapper.deleteByProductSkuIds(groupSaleProductSkuIds);
    }

    @Override
    public void updatePrice(Integer groupSaleProductSkuId, Double price) {
        GroupSaleProductSku groupSaleProductSku = groupSaleProductSkuMapper.selectById(groupSaleProductSkuId);
        if(StringUtils.isNotNull(groupSaleProductSku)){
            // throw new CommonException("数据丢失");
        }
        if(price < groupSaleProductSku.getPrice()){
            // throw new CommonException("商品拼购不得低于最低拼购价");
        }
        groupSaleProductSku.setPrice(price);
        groupSaleProductSkuMapper.updateById(groupSaleProductSku);
    }

    @Override
    @Transactional(rollbackFor = CommonException.class)
    public void chooseProduct(GroupSaleProductSkuVo groupSaleProductSkuVo) {
        List<Integer> natureIds = StringUtils.stringToIntegerList(groupSaleProductSkuVo.getNatureId());
        List<Integer> natureValueIds = StringUtils.stringToIntegerList(groupSaleProductSkuVo.getNatureValueId());
        GroupSaleProduct groupSaleProduct = groupSaleProductMapper.selectById(groupSaleProductSkuVo.getGroupSaleProductId());
        QueryWrapper<ProductSku> productSkuQueryWrapper = new QueryWrapper<>();
        productSkuQueryWrapper.eq("productId",groupSaleProduct.getProductId());
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
            // throw new CommonException("请重新选择拼团规格");
        }
        QueryWrapper<FlashSaleProductSku> flashSaleProductSkuQueryWrapper = new QueryWrapper<>();
        flashSaleProductSkuQueryWrapper.eq("product_id",productSkuIds.get(0));
        List<FlashSaleProductSku> flashSaleProductSkus = flashSaleProductSkuMapper.selectList(flashSaleProductSkuQueryWrapper);
        if(flashSaleProductSkus.size() > 0 ){
            // throw new CommonException("改规格商品已有秒杀记录");
        }
        if(groupSaleProductSkuVo.getGroupSalePrice() < groupSaleProduct.getPrice()){
            // throw new CommonException("商品秒杀价不得低于最低秒杀价");
        }
        GroupSaleProductSku groupSaleProductSku = new GroupSaleProductSku(null,groupSaleProduct.getGroupSaleId(),productSkuIds.get(0),groupSaleProductSkuVo.getGroupSalePrice(),groupSaleProductSkuVo.getGroupSaleProductId());
        groupSaleProductSkuMapper.insert(groupSaleProductSku);
    }
}
