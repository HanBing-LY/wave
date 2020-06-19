package com.liyuan.wave.pms.service.impl;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liyuan.wave.common.cache.constant.ProductCache;
import com.liyuan.wave.common.cache.constant.ProductColumnCache;
import com.liyuan.wave.common.util.StringUtils;
import com.liyuan.wave.common.vo.response.PageInfo;
import com.liyuan.wave.pms.mapper.PmsProductInfoMapper;
import com.liyuan.wave.po.pms.vo.PmsProductInfoVo;
import com.liyuan.wave.pms.service.PmsProductInfoService;
import com.liyuan.wave.pms.po.dto.PmsProductInfoDto;
import com.liyuan.wave.pms.task.ThreadPoolTask;
import com.liyuan.wave.po.pms.PmsProductInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
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

    /**
     * @return
     * @description 查询五级分类下的所有商品
     */
    @Override
    public List<PmsProductInfoVo> listGetAllProductsByMinColumn(Long id, Integer pageNum, Integer pageSize) {
        String s = stringRedisTemplate.opsForValue().get(ProductColumnCache.PRODUCT_PREVIEW_SHOW + id);
        if (StringUtils.isNotEmpty(s)) {
            return JSONArray.parseArray(s, PmsProductInfoVo.class);
        }
        List<PmsProductInfoDto> pmsProductInfoDtos = pmsProductInfoMapper.listGetAllProductsByMinColumn(id, pageNum, pageSize);
        ThreadPoolTask.threadPoolExecutor.submit(()->{
            stringRedisTemplate.opsForValue().set(ProductColumnCache.PRODUCT_PREVIEW_SHOW + id, JSONArray.toJSONString(pmsProductInfoDtos), 30, TimeUnit.DAYS);
        });
        long start = (pageNum - 1) * pageSize;
        long end = pageNum * pageSize;
        List<PmsProductInfoVo> productInfoVos = pmsProductInfoDtos.subList((int) start, (int) end).stream().map(i -> {
            PmsProductInfoVo pmsProductInfoVo = new PmsProductInfoVo();
            BeanUtils.copyProperties(i, pmsProductInfoVo);
            return pmsProductInfoVo;
        }).collect(Collectors.toList());
        return productInfoVos;
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
            pmsProductInfoVo.setSaleNumber(Long.valueOf(Optional.ofNullable(i.getScore()).orElse(0.0).toString()));
            return pmsProductInfoVo;
        }).collect(Collectors.toList());
        PageInfo<PmsProductInfoVo> pageInfo = new PageInfo<>();
        pageInfo.setList(pmsProductInfoVos);
        pageInfo.setTotal(Optional.ofNullable(size).orElse(0L));
        return pageInfo;
    }

    /**
     * @param pageNum
     * @param pageSize
     * @return
     * @description 热搜商品
     */
    @Override
    public PageInfo<PmsProductInfoVo> hotSearchList(Integer pageNum, Integer pageSize) {
        long start = (pageNum - 1) * pageSize;
        long end = pageNum * pageSize;
        Set<ZSetOperations.TypedTuple<String>> rangeWithScores = stringRedisTemplate.opsForZSet().rangeWithScores(ProductCache.HOT_SEARCH_NEAR_SEVEN_DAYS, start, end);
        if (rangeWithScores == null || rangeWithScores.size() == 0) {
            return new PageInfo<>();
        }
        Long size = stringRedisTemplate.opsForZSet().size(ProductCache.HOT_SALE_NEAR_SEVEN_DAYS);

        List<Long> indexList = new ArrayList<>();
        RedisCallback<Object> redisCallback = connection -> {
            StringRedisConnection stringRedisConn = (StringRedisConnection) connection;
            rangeWithScores.forEach(i -> {
                stringRedisConn.get(ProductCache.INFO_DETAIL + i.getValue());
                indexList.add(i.getScore().longValue());
            });
            return null;
        };

        List<Object> objects = stringRedisTemplate.executePipelined(redisCallback);
        if (StringUtils.isEmpty(objects)) {
            return new PageInfo<>();
        }

        AtomicInteger index = new AtomicInteger();
        List<PmsProductInfoVo> collect = objects.stream().map(i -> {
            index.getAndIncrement();
            PmsProductInfoVo pmsProductInfoVo = JSONObject.parseObject(i.toString(), PmsProductInfoVo.class);
            pmsProductInfoVo.setSaleNumber(indexList.get(index.get()));
            return pmsProductInfoVo;
        }).collect(Collectors.toList());

        PageInfo<PmsProductInfoVo> pageInfo = new PageInfo<>();
        pageInfo.setList(collect);
        pageInfo.setTotal(Optional.ofNullable(size).orElse(0L));
        return pageInfo;
    }

    /**
     * @param pageNum
     * @param pageSize
     * @return
     * @description 最新上架商品
     */
    @Override
    public PageInfo<PmsProductInfoVo> listNewPushList(Integer pageNum, Integer pageSize) {
        long start = (pageNum - 1) * pageSize;
        long end = pageNum * pageSize;
        Set<ZSetOperations.TypedTuple<String>> rangeWithScores = stringRedisTemplate.opsForZSet().rangeWithScores(ProductCache.PUSH_TO_SALE, start, end);
        if (rangeWithScores == null || rangeWithScores.size() == 0) {
            return new PageInfo<>();
        }
        Long size = stringRedisTemplate.opsForZSet().size(ProductCache.PUSH_TO_SALE);
        List<Long> indexList = new ArrayList<>();
        RedisCallback<Object> redisCallback = connection -> {
            StringRedisConnection stringRedisConn = (StringRedisConnection) connection;
            rangeWithScores.forEach(i -> {
                stringRedisConn.get(ProductCache.INFO_DETAIL + i.getValue());
                indexList.add(i.getScore().longValue());
            });
            return null;
        };
        List<Object> objects = stringRedisTemplate.executePipelined(redisCallback);
        if (StringUtils.isEmpty(objects)) {
            return new PageInfo<>();
        }

        AtomicInteger index = new AtomicInteger();
        List<PmsProductInfoVo> collect = objects.stream().map(i -> {
            index.getAndIncrement();
            PmsProductInfoVo pmsProductInfoVo = JSONObject.parseObject(i.toString(), PmsProductInfoVo.class);
            pmsProductInfoVo.setPushTime(DateUtil.yearAndQuarter(new Date(indexList.get(index.get()))));
            return pmsProductInfoVo;
        }).collect(Collectors.toList());

        PageInfo<PmsProductInfoVo> pageInfo = new PageInfo<>();
        pageInfo.setList(collect);
        pageInfo.setTotal(Optional.ofNullable(size).orElse(0L));
        return pageInfo;
    }

    /**
     * @param pageNum
     * @param pageSize
     * @return
     * @description 明星商品
     */
    @Override
    public PageInfo<PmsProductInfoVo> starList(Integer pageNum, Integer pageSize) {
        long start = (pageNum - 1) * pageSize;
        long end = pageNum * pageSize;
        Set<ZSetOperations.TypedTuple<String>> rangeWithScores = stringRedisTemplate.opsForZSet().rangeWithScores(ProductCache.CLICK, start, end);
        if (rangeWithScores == null || rangeWithScores.size() == 0) {
            return new PageInfo<>();
        }
        Long size = stringRedisTemplate.opsForZSet().size(ProductCache.PUSH_TO_SALE);
        List<Long> indexList = new ArrayList<>();
        RedisCallback<Object> redisCallback = connection -> {
            StringRedisConnection stringRedisConn = (StringRedisConnection) connection;
            rangeWithScores.forEach(i -> {
                stringRedisConn.get(ProductCache.INFO_DETAIL + i.getValue());
                indexList.add(i.getScore().longValue());
            });
            return null;
        };
        List<Object> objects = stringRedisTemplate.executePipelined(redisCallback);
        if (StringUtils.isEmpty(objects)) {
            return new PageInfo<>();
        }
        AtomicInteger index = new AtomicInteger();
        List<PmsProductInfoVo> collect = objects.stream().map(i -> {
            index.getAndIncrement();
            PmsProductInfoVo pmsProductInfoVo = JSONObject.parseObject(i.toString(), PmsProductInfoVo.class);
            pmsProductInfoVo.setPushTime(DateUtil.yearAndQuarter(new Date(indexList.get(index.get()))));
            return pmsProductInfoVo;
        }).collect(Collectors.toList());

        PageInfo<PmsProductInfoVo> pageInfo = new PageInfo<>();
        pageInfo.setList(collect);
        pageInfo.setTotal(Optional.ofNullable(size).orElse(0L));
        return pageInfo;
    }
}