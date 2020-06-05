package com.langchao.waveservicemall.service.impl;

import com.chemguan.business.service.ServiceImpl;
import com.chemguan.dao.ProductInfoMapper;
import com.chemguan.dao.RecommendVideoProductMapper;
import com.chemguan.entity.RecommendVideoProduct;
import com.chemguan.entity.RecommendVideoProductDto;
import com.chemguan.service.RecommendVideoProductService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * @Title: RecommendVideoProductServiceImpl
 * @ProjectName
 * @Description: TODO
 * @author 
 * @date Mon Feb 17 16:34:44 CST 2020
 */
@Service
@Transactional
public class RecommendVideoProductServiceImpl extends ServiceImpl<RecommendVideoProduct> implements RecommendVideoProductService {
    @Autowired
    private RecommendVideoProductMapper RecommendVideoProductMapper;
    @Autowired
    private ProductInfoMapper productInfoMapper;

    @Override
    public IPage findManagerList(Integer page, Integer size) {
        PageHelper.startPage(page,size);
        List<RecommendVideoProductDto> managerList = RecommendVideoProductMapper.findManagerList();
        for (RecommendVideoProductDto recommendVideoProductDto : managerList) {
            if (recommendVideoProductDto.getProductId() != null) {
                recommendVideoProductDto.setProductName(productInfoMapper.selectByPrimaryKey(recommendVideoProductDto.getProductId()).getProductName());
            }
        }
       IPage<RecommendVideoProductDto> pageInfo = newIPage<>(managerList);
        return pageInfo;
    }
}
