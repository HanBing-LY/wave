package com.langchao.waveservicemall.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.langchao.waveservicemall.mapper.ColumnNatureMapper;
import com.langchao.waveservicemall.mapper.NatureValueMapper;
import com.langchao.waveservicemall.mapper.ProductInfoMapper;
import com.langchao.waveservicemall.pojo.ColumnNature;
import com.langchao.waveservicemall.pojo.NatureValue;
import com.langchao.waveservicemall.pojo.ProductInfo;
import com.langchao.waveservicemall.pojo.dto.ColumnNatureDTO;
import com.langchao.waveservicemall.service.ColumnNatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class ColumnNatureServiceImpl extends ServiceImpl<ColumnNatureMapper,ColumnNature> implements ColumnNatureService {
    @Autowired
    private ColumnNatureMapper columnNatureMapper;
    @Autowired
    private ProductInfoMapper productInfoMapper;
    @Autowired
    private NatureValueMapper natureValueMapper;


    @Override
    public IPage findManagerList(Integer productColumId, Integer page, Integer size) {
        IPage<ColumnNature> iPage = new Page<>(page, size);
        QueryWrapper<ColumnNature> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("product_colum_id",productColumId);
        return columnNatureMapper.selectPage(iPage, queryWrapper);
    }

    @Override
    public List<ColumnNatureDTO> findByColumnId(Integer productColumId,String columnNatureId,String natureValueId) {
        ProductInfo productInfo = productInfoMapper.selectById(productColumId);
        Map map = new HashMap();
        map.put("id",productInfo.getProductColumId());
        List<ColumnNatureDTO> managerList = columnNatureMapper.findManagerList(map);
        for (ColumnNatureDTO columnNatureDTO : managerList) {
            if(columnNatureDTO.getId() != null){
                QueryWrapper<NatureValue> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("natureId",columnNatureDTO.getId());
                List<NatureValue> natureValues = natureValueMapper.selectList(queryWrapper);
                columnNatureDTO.setNatureValueList(natureValues);
            }
        }
        return managerList;

    }

    @Override
    public Integer findCountByColumnId(Integer columnId) {
        return columnNatureMapper.findCountByColumnId(columnId);
    }
}
