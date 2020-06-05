package com.langchao.waveservicemall.service;


import com.langchao.waveservicemall.pojo.dto.ProductEvaluateDTO;

/**
 * @Title: ProductEvaluateService
 * @ProjectName
 * @Description: TODO
 * @author 
 * @date Mon Feb 17 16:34:44 CST 2020
 */
public interface ProductEvaluateService  {

    /**
     * @Author liyuan
     * @Description 根据产品id 分页查询
     * @param productId 产品Id
     * @param pageNum
     * @param pageSize
     * @return
     */
    ProductEvaluateDTO listProductEvaluateByPage(Integer productId, Integer pageNum, Integer pageSize);

}
