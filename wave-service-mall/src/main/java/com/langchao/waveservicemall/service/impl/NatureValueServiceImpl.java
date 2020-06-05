package com.langchao.waveservicemall.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.langchao.waveservicemall.mapper.NatureValueMapper;
import com.langchao.waveservicemall.mapper.ProductInfoMapper;
import com.langchao.waveservicemall.pojo.NatureValue;
import com.langchao.waveservicemall.service.NatureValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Title: NatureValueServiceImpl
 * @ProjectName
 * @Description: TODO
 * @author 
 * @date Mon Feb 17 16:34:44 CST 2020
 */
@Service
@Transactional
public class NatureValueServiceImpl extends ServiceImpl<NatureValueMapper, NatureValue> implements NatureValueService {
    @Autowired
    private NatureValueMapper natureValueMapper;
    @Autowired
    private ProductInfoMapper productInfoMapper;

    @Override
    public IPage findManagerList(Integer id, Integer page, Integer size) {
        IPage<NatureValue> iPage = new Page<NatureValue>(page,size);
        QueryWrapper<NatureValue> natureValueQueryWrapper = new QueryWrapper<>();
        natureValueQueryWrapper.eq("id",id);
        return natureValueMapper.selectPage(iPage,natureValueQueryWrapper);
    }

}
