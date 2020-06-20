package com.liyuan.wave.sms.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liyuan.wave.common.cache.constant.GroupSaleCache;
import com.liyuan.wave.common.exception.ExceptionCast;
import com.liyuan.wave.common.util.StringUtils;
import com.liyuan.wave.po.sms.SmsGroupInfo;
import com.liyuan.wave.sms.common.SmsExceptionCode;
import com.liyuan.wave.sms.mapper.SmsGroupInfoMapper;
import com.liyuan.wave.sms.po.dto.SmsGroupSaleProductDto;
import com.liyuan.wave.sms.po.vo.SmsGroupInfoDetailVo;
import com.liyuan.wave.sms.po.vo.SmsGroupInfoVo;
import com.liyuan.wave.sms.po.vo.SmsGroupSaleProductVo;
import com.liyuan.wave.sms.service.SmsGroupInfoService;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @author liyuan
 * @description sms_group_info
 * @email 724837404@qq.com
 * @date 2020-06-15 16:09:35
 */
@Service("smsGroupInfoService")
public class SmsGroupInfoServiceImpl extends ServiceImpl<SmsGroupInfoMapper, SmsGroupInfo> implements SmsGroupInfoService {

    @Autowired
    private SmsGroupInfoMapper smsGroupInfoMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedissonClient redissonClient;

    @Override
    public List<SmsGroupInfoVo> listGroupClubByPage(String articleNumber, boolean flag) {
        // 加载商品下的所有团
        Collection<String> members;
        if (flag) {
            // 所有的团
            members = stringRedisTemplate.opsForSet().members(GroupSaleCache.GROUP_TEAM_OF_PRODUCT + articleNumber);
        } else {
            // 随机两个
            members = stringRedisTemplate.opsForSet().randomMembers(GroupSaleCache.GROUP_TEAM_OF_PRODUCT + articleNumber, 2);
        }
        if (StringUtils.isNull(members)) {
            return new ArrayList<>();
        }

        // 计算各团人数
        List<String> indexList = new ArrayList<>();
        RedisCallback<Object> redisCallback = connection -> {
            StringRedisConnection stringRedisConnection = (StringRedisConnection) connection;
            members.forEach(i -> {
                stringRedisConnection.hLen(GroupSaleCache.GROUP_MEMBERS_OF_TEAM + i);
                indexList.add(i);
            });
            return null;
        };

        List<Object> objects = stringRedisTemplate.executePipelined(redisCallback);
        if (StringUtils.isEmpty(objects)) {
            return new ArrayList<>();
        }

        AtomicInteger index = new AtomicInteger();
        List<SmsGroupInfoVo> collect = objects.stream().map(i -> {
            index.getAndIncrement();
            SmsGroupInfoVo smsGroupInfoVo = new SmsGroupInfoVo();
            smsGroupInfoVo.setGroupNumber(indexList.get(index.get()));
            smsGroupInfoVo.setPeople(Long.valueOf(i.toString()));
            return smsGroupInfoVo;
        }).collect(Collectors.toList());

        return collect;
    }

    @Override
    public SmsGroupInfoDetailVo groupClubByGroupNumber(String groupNumber) {
        // 团所有的成员
        Set<Object> keys = stringRedisTemplate.opsForHash().keys(GroupSaleCache.GROUP_MEMBERS_OF_TEAM + groupNumber);
        return null;
    }

    @Override
    public void joinGroup(String groupNumber) {
        String self = GroupSaleCache.GROUP_MEMBERS_OF_TEAM + groupNumber;
        Long size = stringRedisTemplate.opsForHash().size(self);
        String lockKey = self + "_lock";
        boolean flag = false;
        // 双重校验锁
        if (size < 10) {
            RLock redissonLock = redissonClient.getLock(lockKey);
            try {
                redissonLock.lock();
                if (stringRedisTemplate.opsForHash().size(self) < 10) {
                    stringRedisTemplate.opsForHash().put(self, "user", 1);
                    // TODO 生成订单
                    flag = true;
                }
            } finally {
                redissonLock.unlock();
            }
        }
        if (!flag) {
            ExceptionCast.cast(SmsExceptionCode.GROUP_IS_ALL);
        }
    }

    @Override
    public void createGroup(String articleNumber, Long groupSaleProductId) {
        String s = stringRedisTemplate.opsForValue().get(GroupSaleCache.INFO_PRODUCT + articleNumber);
        SmsGroupInfo smsGroupInfo = new SmsGroupInfo();
        if (StringUtils.isNotEmpty(s)) {
            SmsGroupSaleProductVo smsGroupSaleProductVo = JSONObject.parseObject(s, SmsGroupSaleProductVo.class);
            smsGroupInfo.setGroupPeople(Optional.ofNullable(smsGroupSaleProductVo).orElse(new SmsGroupSaleProductVo()).getGroupPeople());
        } else {
            // 缓存没有先走布隆过滤器
            RBloomFilter<Object> bloomFilter = redissonClient.getBloomFilter(GroupSaleCache.BLOOM_FILTER);
            boolean contains = bloomFilter.contains(articleNumber);
            if (!contains) {
                ExceptionCast.cast(SmsExceptionCode.GROUP_PRODUCT_NOT_EXIST);
            }
            SmsGroupSaleProductDto smsGroupSaleProductDto = smsGroupInfoMapper.selectByArticleNumber(articleNumber);
            smsGroupInfo.setGroupPeople(smsGroupSaleProductDto.getGroupPeople());
        }
        smsGroupInfo.setGroupSaleProductId(groupSaleProductId);
        smsGroupInfo.setStatus((byte) 1);
        smsGroupInfoMapper.insert(smsGroupInfo);
    }
}