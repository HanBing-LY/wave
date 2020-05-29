package com.example.demoredis.test;

import org.redisson.Redisson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.Jedis;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author liyuan
 * @create 2019-12-29-23:17
 */
public class StringTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private Redisson redisson;

    @Autowired
    private Jedis jedis;

    private String key = "存入的key键";
    private String value = "需要存入的值value";
    private long expireTime = 60 * 60 * 24 * 30;

    public void stringToUser() {

        // TimeUnit.SECONDS过期时间添加随机值 ,防止缓存集体过期,雪崩
        // 或者key 指定范围之间的字符串
        String s = stringRedisTemplate.opsForValue().get(key, 1, 4);
        // 注意:!!! 下面一个方法(参数类型不一样)  覆盖指定位置的字符串,替换 10后面的长度
        stringRedisTemplate.opsForValue().set(key,value,10);
        // 注意:!!! 上面一个方法(参数类型不一样)  设置多久后过期
        stringRedisTemplate.opsForValue().set(key,value, Duration.ZERO);
        // set方法key 和value必须
        stringRedisTemplate.opsForValue().set(key, value, expireTime, TimeUnit.SECONDS);
        // setIfAbsent方法 如果缓存没有这个key,设值成功会返回true,否则返回false   通常分布式锁
        Boolean setIfAbsent = stringRedisTemplate.opsForValue().setIfPresent(key, value, 60 * 60 * 24 * 30, TimeUnit.SECONDS);
        // 同上相反,存在就设值
        Boolean setIfPresent = stringRedisTemplate.opsForValue().setIfPresent(key, value, 60 * 60 * 24 * 30, TimeUnit.SECONDS);
        // 把原本的值返回,将新值覆盖进去
        String oldValue = stringRedisTemplate.opsForValue().getAndSet(key, value);
        // 取出多个值
        List<String> stringList = stringRedisTemplate.opsForValue().multiGet(new ArrayList<>());
        // 设置多个值.底层用的set结构放的
        stringRedisTemplate.opsForValue().multiSet(new HashMap<>(16));
        // 往key上自增多少,默认1
        Long increment = stringRedisTemplate.opsForValue().increment(key, 100);
        // 往key下自减多少,默认1
        Long decrement = stringRedisTemplate.opsForValue().decrement(key, 100);
        // 字符串后面追加,拼接
        Integer append = stringRedisTemplate.opsForValue().append(key, value);
        // key件的长度
        Long size = stringRedisTemplate.opsForValue().size(key);

    }

}
