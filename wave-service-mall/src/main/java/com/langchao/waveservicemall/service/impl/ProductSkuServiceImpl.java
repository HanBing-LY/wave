package com.langchao.waveservicemall.service.impl;

import com.chemguan.business.service.ServiceImpl;
import com.chemguan.dao.*;
import com.chemguan.entity.*;
import com.chemguan.service.ProductSkuService;
import GuavaUtil;
import StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * @author
 * @Title: ProductSkuServiceImpl
 * @ProjectName
 * @Description: TODO
 * @date Mon Feb 17 16:34:44 CST 2020
 */
@Service
@Transactional
public class ProductSkuServiceImpl extends ServiceImpl<ProductSku> implements ProductSkuService {
    @Autowired
    private ProductSkuMapper productSkuMapper;
    @Autowired
    private ProductInfoMapper productInfoMapper;
    @Autowired
    private ProductSkuNatureMapper productSkuNatureMapper;
    @Autowired
    private ColumnNatureMapper columnNatureMapper;
    @Autowired
    private NatureValueMapper natureValueMapper;

    /**
     * @Description 后台list
     * @Author Renjinliang
     * @date 2020/3/24 9:08
     */
    @Override
    public IPage findManagerList(Integer page, Integer size, Integer id) {
        PageHelper.startPage(page, size);
        List<ProductSkuDto> managerList = productSkuMapper.findManagerList(id);
        for (ProductSkuDto productSkuDto : managerList) {

            ProductSkuNature productSkuNature = productSkuDto.getProductSkuNature();
//            根据分类属性id查询list数据
            Map map = new HashMap();
            map.put("productSkuId", productSkuNature.getProductSkuId());
            List<ProductSkuNature> natureList = productSkuNatureMapper.findByIds(map);
            List<String> stringList = new ArrayList<>();
            List<String> natureValueList = new ArrayList<>();
            for (ProductSkuNature skuNature : natureList) {
                stringList.add(columnNatureMapper.selectByPrimaryKey(skuNature.getColumnNatureId()).getNatureName());
                natureValueList.add(natureValueMapper.selectByPrimaryKey(skuNature.getNatureValueId()).getValueDesc());
            }

            productSkuDto.setColumnName(String.join(",", stringList));//分类属性
            productSkuDto.setValueName(String.join(",", natureValueList));//分类属性值
        }
       IPage<ProductSkuDto> pageInfo = newIPage<>(managerList);
        return pageInfo;
    }


    /**
     * @param id             产品id
     * @param stock          库存
     * @param columnNatureId 分类属性id
     * @param natureValueId  分类属性值id
     * @Description
     * @Author Renjinliang
     * @date 2020/3/24 20:55
     */
    @Override
    public void addProductSku(Integer id, Integer stock, String columnNatureId, String natureValueId) {

        //添加库存  获取产品
        ProductInfo productInfo = productInfoMapper.selectByPrimaryKey(id);


        List<ProductSkuDto> productSkuDtoList = productSkuMapper.findByProductId(id);


        //添加分类属性id和分类属性值id
        List<String> idList = new ArrayList<>();
        if (StringUtils.isNotEmpty(columnNatureId)) {
            idList = GuavaUtil.splitter(columnNatureId, ",");

        }
        List<String> valueList = new ArrayList<>();
        if (StringUtils.isNotEmpty(natureValueId)) {
            valueList = GuavaUtil.splitter(natureValueId, ",");

        }
        Integer skuId = null;
        for (ProductSkuDto productSkuDto : productSkuDtoList) {
            List<ProductSkuNatureDTO> natureDTOList = productSkuNatureMapper.findByProductSkuId(productSkuDto.getId());
            List<String> dtoidList = new ArrayList<>();
            List<String> dtovalueList = new ArrayList<>();
            for (ProductSkuNatureDTO productSkuNatureDTO : natureDTOList) {
                dtoidList.add(productSkuNatureDTO.getColumnNatureId().toString());
                dtovalueList.add(productSkuNatureDTO.getNatureValueId().toString());
            }
            if (dtoidList.stream().sorted().collect(Collectors.joining())
                    .equals(idList.stream().sorted().collect(Collectors.joining())) &&
                    valueList.stream().sorted().collect(Collectors.joining())
                            .equals(dtovalueList.stream().sorted().collect(Collectors.joining()))) {
                skuId = productSkuDto.getId();
                break;
            }
        }


        if (skuId != null) {
            //根据产品skuId查询产品sku
            ProductSku productSku = productSkuMapper.selectByPrimaryKey(skuId);
            productSkuMapper.updateById(productSku.getId(), stock);
        } else {
            ProductSku productSku = new ProductSku();
            productSku.setProductId(productInfo.getId());
            productSku.setStock(stock);//库存
            productSku.setProductPrice(productInfo.getProductSalePrice());//产品售价
            productSku.setVipPrice(productInfo.getVipPrice());//会员价
            productSku.setClubChargePrice(productInfo.getClubChargePrice());//俱乐部负责人价
            productSku.setPercentage(productInfo.getPercentage());//提成比例
            save(productSku);

            List<ProductSkuNature> list = new ArrayList<>();
            for (int i = 0; i < idList.size(); i++) {
                Integer ids = Integer.parseInt(idList.get(i));
                Integer value = Integer.parseInt(valueList.get(i));
                ProductSkuNature productSkuNature = new ProductSkuNature();
                productSkuNature.setColumnNatureId(ids);
                productSkuNature.setNatureValueId(value);
                productSkuNature.setProductSkuId(productSku.getId());
                list.add(productSkuNature);
            }
            productSkuNatureMapper.insertList(list);
        }


    }
}
