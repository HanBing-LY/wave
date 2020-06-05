package com.langchao.waveservicemall.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.langchao.waveservicemall.mapper.ShopBannerMapper;
import com.langchao.waveservicemall.pojo.ShopBanner;
import com.langchao.waveservicemall.service.ShopBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Title: ShopBannerServiceImpl
 * @ProjectName
 * @Description: TODO
 * @author 
 * @date Mon Feb 17 16:34:44 CST 2020
 */
@Service
public class ShopBannerServiceImpl extends ServiceImpl<ShopBannerMapper,ShopBanner> implements ShopBannerService {
    @Autowired
    private ShopBannerMapper shopBannerMapper;

    @Override
    public IPage findManagerList(Integer page,Integer size) {
        IPage<ShopBanner> iPage = new Page<>(page, size);
        return shopBannerMapper.selectPage(iPage, new QueryWrapper<ShopBanner>());
    }
}
