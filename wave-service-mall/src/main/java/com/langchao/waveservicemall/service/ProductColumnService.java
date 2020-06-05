package com.langchao.waveservicemall.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.langchao.waveservicemall.pojo.ProductColumn;

import java.util.List;

/**
 * @Title: ProductColumnService
 * @ProjectName
 * @Description: TODO
 * @author 
 * @date Mon Feb 17 16:34:44 CST 2020
 */
public interface ProductColumnService extends IService<ProductColumn> {

    /**
     * @description 获取商品分类
     * @param
     * @return
     **/
    



    /**
    *@Description 后台添加
    *@Author Renjinliang
    *@date 2020/3/20 17:05
    */
    void addProductColumn(ProductColumn productColumn);

    /**
     *@Description 后台list
     *@Author Renjinliang
     *@date 2020/3/20 16:07
     */
   IPage findManagerList(Integer page, Integer size);

    /**
     * 根据分类id查询下面自分类
     * @param columnId
     * @return
     */
    List<ProductColumn> findbyparent(Integer columnId);
}
