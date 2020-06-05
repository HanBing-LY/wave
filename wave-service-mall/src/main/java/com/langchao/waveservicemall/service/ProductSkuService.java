package com.langchao.waveservicemall.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Title: ProductSkuService
 * @ProjectName
 * @Description: TODO
 * @author 
 * @date Mon Feb 17 16:34:44 CST 2020
 */
public interface ProductSkuService  extends IService {
    /**
    *@Description 后台list
    *@Author Renjinliang
    *@date 2020/3/24 10:18
    */
   IPage findManagerList(Integer page, Integer size, Integer id);


    /**
     *@Description
     *@Author Renjinliang
     *@date 2020/3/24 20:55
     * @param id 产品id
     * @param stock 库存
     * @param columnNatureId 分类属性id
     * @param natureValueId 分类属性值id
     */
    void addProductSku(Integer id, Integer stock, String columnNatureId, String natureValueId);
}
