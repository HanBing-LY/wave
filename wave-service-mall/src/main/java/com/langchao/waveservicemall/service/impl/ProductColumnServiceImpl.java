package com.langchao.waveservicemall.service.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.langchao.waveservicemall.mapper.ProductColumnMapper;
import com.langchao.waveservicemall.pojo.ProductColumn;
import com.langchao.waveservicemall.pojo.dto.ProductColumnDto;
import com.langchao.waveservicemall.service.ProductColumnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Title: ProductColumnServiceImpl
 * @ProjectName
 * @Description: TODO
 * @author 
 * @date Mon Feb 17 16:34:44 CST 2020
 */
@Service
@Transactional
public class ProductColumnServiceImpl extends ServiceImpl<ProductColumnMapper, ProductColumn> implements ProductColumnService {
    @Autowired
    private ProductColumnMapper productColumnMapper;

    /**
    *@Description 后台添加
    *@Author Renjinliang
    *@date 2020/3/20 17:04
    */
    @Override
    public void addProductColumn(ProductColumn productColumn){
        save(productColumn);
    }



    /**
    *@Description 后台list
    *@Author Renjinliang
    *@date 2020/3/20 16:53
    */
    @Override
    public IPage<ProductColumnDto> findManagerList(Integer pageNum, Integer pageSize) {
        IPage<ProductColumnDto> iPage = new Page<ProductColumnDto>(pageNum,pageSize);
        List<ProductColumnDto> managerList = productColumnMapper.findManagerList();
        for (ProductColumnDto productColumnDto : managerList) {
            if(productColumnDto.getId() != null){
                Map map = new HashMap();
                map.put("id",productColumnDto.getId());
                List<ProductColumnDto> managerListById = productColumnMapper.findManagerListById(map);
                productColumnDto.setColumnList(managerListById);
            }
        }
        iPage.setRecords(managerList);
        return iPage;
    }

    @Override
    public List<ProductColumn> findbyparent(Integer columnId) {
        return productColumnMapper.findbyparent(columnId);
    }
}
