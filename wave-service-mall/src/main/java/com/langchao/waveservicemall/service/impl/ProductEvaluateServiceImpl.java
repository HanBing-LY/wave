package com.langchao.waveservicemall.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.langchao.wavecommon.util.StringUtils;
import com.langchao.waveservicemall.constant.PageControlInformations;
import com.langchao.waveservicemall.mapper.ProductEvaluateMapper;
import com.langchao.waveservicemall.pojo.ProductEvaluate;
import com.langchao.waveservicemall.pojo.dto.ProductEvaluateDTO;
import com.langchao.waveservicemall.service.ProductEvaluateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Title: ProductEvaluateServiceImpl
 * @ProjectName
 * @Description: TODO
 * @author 
 * @date Mon Feb 17 16:34:44 CST 2020
 */
@Service
public class ProductEvaluateServiceImpl extends ServiceImpl<ProductEvaluateMapper, ProductEvaluate> implements ProductEvaluateService {

    @Autowired
    private ProductEvaluateMapper productEvaluateMapper;

    /**
     * @Author liyuan
     * @Description 根据产品id 分页查询
     * @param productId 产品Id
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public ProductEvaluateDTO listProductEvaluateByPage(Integer productId, Integer pageNum, Integer pageSize) {
        if(StringUtils.isNull(pageNum) || pageNum < 1){
            pageNum = PageControlInformations.DEFAULT_PAGE_NUMBER;
        }
        if(StringUtils.isNull(pageSize) || pageSize < 1 || pageSize > PageControlInformations.MAX_PAGE_SIZE){
            pageSize = PageControlInformations.EVALUATE_DEFAULT_PAGE_SIZE;
        }
        QueryWrapper<ProductEvaluate> productEvaluateQueryWrapper = new QueryWrapper<>();
        productEvaluateQueryWrapper.eq("productId", productId);
        productEvaluateQueryWrapper.orderByAsc("addtime");
        List<ProductEvaluate> productEvaluates = productEvaluateMapper.selectList(productEvaluateQueryWrapper);
        ProductEvaluateDTO productEvaluateDTO = new ProductEvaluateDTO();
        //好评率默认为-
        productEvaluateDTO.setPositiveRating("-");
        if(productEvaluates.size() > 0){
            //有评论
            List<ProductEvaluate> collect = productEvaluates.stream().filter(i -> i.getStar() == 5).collect(Collectors.toList());
            //        评论总人数
            int sizeAll = productEvaluates.size();
            //        五星评论人数
            int size = collect.size();
            // 创建一个数值格式化对象
            NumberFormat numberFormat = NumberFormat.getInstance();
            // 设置精确到小数点后2位
            numberFormat.setMaximumFractionDigits(2);
            String result = numberFormat.format((float) size / (float) sizeAll * 100);
            productEvaluateDTO.setPositiveRating(result + "%");
            List<Integer> productEvaluateIds = new ArrayList<>();
//            抽取所有的评论id(可能出现NPE)
            productEvaluates.forEach(i -> productEvaluateIds.add(Optional.ofNullable(i.getId()).get()));
            IPage<ProductEvaluateDTO> iPage = new Page<>(pageNum,pageSize);
            List<ProductEvaluateDTO> productEvaluateDTOList =productEvaluateMapper.commonSelectByIds(iPage,productEvaluateIds);
            productEvaluateDTO.setProductEvaluateDTOList(productEvaluateDTOList);
        }
        return productEvaluateDTO;
    }

}
