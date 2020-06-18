package com.liyuan.wave.pms.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.liyuan.wave.pms.po.vo.PmsProductInfoVo;
import com.liyuan.wavecommon.cache.constant.ProductCache;
import com.liyuan.wavecommon.util.StringUtils;
import com.liyuan.wavecommon.vo.response.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.liyuan.wave.pms.mapper.PmsProductInfoMapper;
import com.liyuan.wave.po.pms.PmsProductInfo;
import com.liyuan.wave.pms.service.PmsProductInfoService;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author liyuan
 * @description pms_product_info
 * @email 724837404@qq.com
 * @date 2020-06-15 16:10:20
 */
@Service("pmsProductInfoService")
public class PmsProductInfoServiceImpl extends ServiceImpl<PmsProductInfoMapper, PmsProductInfo> implements PmsProductInfoService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private PmsProductInfoMapper pmsProductInfoMapper;

    @Override
    public PageInfo<PmsProductInfoVo> listGetAllProductsByMinColumn(Long id) {
        return null;
    }

    @Override
    public PageInfo<PmsProductInfoVo> listHotSaleList(Integer pageNum, Integer pageSize) {
        long start = (pageNum - 1) * pageSize;
        long end = pageNum * pageSize;
        Set<ZSetOperations.TypedTuple<String>> rangeWithScores = stringRedisTemplate.opsForZSet().rangeWithScores(ProductCache.HOT_SALE_NEAR_SEVEN_DAYS, start, end);
        if (StringUtils.isEmpty(rangeWithScores)) {
            return new PageInfo<>();
        }
        Long size = stringRedisTemplate.opsForZSet().size(ProductCache.HOT_SALE_NEAR_SEVEN_DAYS);
        List<PmsProductInfoVo> pmsProductInfoVos = rangeWithScores.stream().map(i -> {
            PmsProductInfoVo pmsProductInfoVo = JSONObject.parseObject(i.getValue(), PmsProductInfoVo.class);
            pmsProductInfoVo.setScore(Long.valueOf(Optional.ofNullable(i.getScore()).orElse(0.0).toString()));
            return pmsProductInfoVo;
        }).collect(Collectors.toList());
        PageInfo<PmsProductInfoVo> pageInfo = new PageInfo<>();
        pageInfo.setList(pmsProductInfoVos);
        pageInfo.setTotal(Optional.ofNullable(size).orElse(0L));
        return pageInfo;
    }

    @Override
    public PageInfo<PmsProductInfoVo> hotSearchList(Integer pageNum, Integer pageSize) {
        long start = (pageNum - 1) * pageSize;
        long end = pageNum * pageSize;
        Set<ZSetOperations.TypedTuple<String>> rangeWithScores = stringRedisTemplate.opsForZSet().rangeWithScores(ProductCache.HOT_SEARCH_NEAR_SEVEN_DAYS, start, end);
        if (StringUtils.isEmpty(rangeWithScores)) {
            return new PageInfo<>();
        }
        Long size = stringRedisTemplate.opsForZSet().size(ProductCache.HOT_SALE_NEAR_SEVEN_DAYS);
        List<PmsProductInfoVo> pmsProductInfoVos = rangeWithScores.stream().map(i -> {
            PmsProductInfoVo pmsProductInfoVo = JSONObject.parseObject(i.getValue(), PmsProductInfoVo.class);
            pmsProductInfoVo.setScore(Long.valueOf(Optional.ofNullable(i.getScore()).orElse(0.0).toString()));
            return pmsProductInfoVo;
        }).collect(Collectors.toList());
        PageInfo<PmsProductInfoVo> pageInfo = new PageInfo<>();
        pageInfo.setList(pmsProductInfoVos);
        pageInfo.setTotal(Optional.ofNullable(size).orElse(0L));
        return pageInfo;
    }

    @Override
    public PageInfo<PmsProductInfoVo> listNewPushList(Integer pageNum, Integer pageSize) {
        long start = (pageNum - 1) * pageSize;
        long end = pageNum * pageSize;
        Set<String> ids = stringRedisTemplate.opsForZSet().reverseRange(ProductCache.PUSH_TO_SALE, start, end);



        return null;
    }

    @Override
    public PageInfo<PmsProductInfoVo> starList(Integer pageNum, Integer pageSize) {
        return null;
    }
}