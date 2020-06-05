package com.langchao.waveservicemall.service.impl;

import com.chemguan.business.service.ServiceImpl;
import com.chemguan.dao.UserRecommendProductMapper;
import com.chemguan.entity.UserRecommendProduct;
import com.chemguan.service.UserRecommendProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * @Title: UserRecommendProductServiceImpl
 * @ProjectName
 * @Description: TODO
 * @author 
 * @date Mon Feb 17 16:34:44 CST 2020
 */
@Service
@Transactional
public class UserRecommendProductServiceImpl extends ServiceImpl<UserRecommendProduct> implements UserRecommendProductService {
    @Autowired
    private UserRecommendProductMapper UserRecommendProductMapper;

}
