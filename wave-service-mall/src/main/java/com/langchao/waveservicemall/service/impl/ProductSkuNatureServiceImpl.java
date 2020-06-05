package com.langchao.waveservicemall.service.impl;

import com.chemguan.business.exception.ServiceException;
import com.chemguan.business.service.ServiceImpl;
import com.chemguan.dao.ColumnNatureMapper;
import com.chemguan.dao.NatureValueMapper;
import com.chemguan.dao.ProductSkuNatureMapper;
import com.chemguan.dao.ProductSkuMapper;
import com.chemguan.entity.ColumnNatureDTO;
import com.chemguan.entity.ProductSku;
import com.chemguan.entity.ProductSkuNature;
import com.chemguan.entity.ProductSkuNatureDTO;
import com.chemguan.service.ProductSkuNatureService;
import StringUtils;
import com.chemguan.vo.ProductVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * @Title: ProductSkuNatureServiceImpl
 * @ProjectName
 * @Description: TODO
 * @author 
 * @date Mon Feb 17 16:34:44 CST 2020
 */
@Service
@Transactional
public class ProductSkuNatureServiceImpl extends ServiceImpl<ProductSkuNature> implements ProductSkuNatureService {
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
     * @Description 根据产品id 列举产品属性信息
     * @param productId
     * @return
     */
    @Override
    public List<ColumnNatureDTO> selectProductNormByProductId(Integer productId) {
        List<ColumnNatureDTO> columnNatureDTOList = productSkuNatureMapper.selectProductNormByProductId(productId);
        return columnNatureDTOList;
    }

    /**
    *@Description 后台list
    *@Author Renjinliang
    *@date 2020/3/24 11:11
    */
    @Override
    public IPage findManagerList(Integer page, Integer size, Integer id) {
        PageHelper.startPage(page,size);
        Map map = new HashMap();
        if(id != null){
            map.put("id",id);
        }
        List<ProductSkuNatureDTO> managerList = productSkuNatureMapper.findManagerList(map);
        for (ProductSkuNatureDTO productSkuNatureDTO : managerList) {
            if(productSkuNatureDTO.getColumnNatureId() != null){
                //分类属性名称
                productSkuNatureDTO.setColumnName(columnNatureMapper.selectByPrimaryKey(productSkuNatureDTO.getColumnNatureId()).getNatureName());
            }
            if(productSkuNatureDTO.getNatureValueId() != null){
                //分类属性值名称
                productSkuNatureDTO.setNatureName(natureValueMapper.selectByPrimaryKey(productSkuNatureDTO.getNatureValueId()).getValueDesc());
            }
        }
       IPage<ProductSkuNatureDTO> pageInfo = newIPage<>(managerList);
        return pageInfo;
    }

    /**
     * @author liyuan
     * @Description 计算商品库存
     * @param productVo
     * @return
     */
    @Override
    public Integer getStock(ProductVo productVo) {
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
        ProductSku productSku = productSkuMapper.selectByPrimaryKey(productSkuIds.get(0));
        if(StringUtils.isNull(productSku)){
            // throw new CommonException("商品丢失");
        }
        return productSku.getStock();
    }
}
