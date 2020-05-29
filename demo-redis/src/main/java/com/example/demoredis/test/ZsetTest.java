package com.example.demoredis.test;

import org.redisson.Redisson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.Jedis;

/**
 * @author liyuan
 * @create 2019-12-30-10:10
 */
public class ZsetTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private Redisson redisson;

    @Autowired
    private Jedis jedis;

    private String key = "存入的key键";
    private String value = "需要存入的值value";
    private long expireTime = 60 * 60 * 24 * 30;

}
