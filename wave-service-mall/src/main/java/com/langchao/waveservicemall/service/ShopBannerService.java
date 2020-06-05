package com.langchao.waveservicemall.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.langchao.waveservicemall.pojo.ShopBanner;

/**
 * @Title: ShopBannerService
 * @ProjectName
 * @Description: TODO
 * @author 
 * @date Mon Feb 17 16:34:44 CST 2020
 */
public interface ShopBannerService extends IService<ShopBanner> {

    /**
    *@Description 后台list
    *@Author Renjinliang
    *@date 2020/3/27 8:57
    */
   IPage findManagerList(Integer page, Integer size);
}
