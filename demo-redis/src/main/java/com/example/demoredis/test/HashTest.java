package com.example.demoredis.test;

import org.redisson.Redisson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

/**
 * @author liyuan
 * @create 2019-12-30-10:17
 */
public class HashTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private Redisson redisson;

    @Autowired
    private Jedis jedis;

    private String key = "存入的key键";
    private String value = "需要存入的值value";
    private long expireTime = 60 * 60 * 24 * 30;

    /**
     * hget key field
     * 返回哈希表 key 中给定域 field 的值。
     */
    public void get() {
        stringRedisTemplate.opsForHash().get(key,value);
    }

    public void set() {

    }

    /**
     * 返回哈希表 key 中的所有域
     */
    public void keys() {
        stringRedisTemplate.opsForHash().keys(key);
    }

    /**
     * hdel key field [field ...]
     * 删除哈希表 key 中的一个或多个指定域，不存在的域将被忽略。
     */
    public void delete() {
        stringRedisTemplate.opsForHash().delete(key,"1","2");
    }

    /**
     * hexists key field
     * 查看哈希表 key 中，给定域 field 是否存在。
     */
    public void hasKey() {
        stringRedisTemplate.opsForHash().hasKey(key,value);
    }

    /**
     * hmget key field [field ...]
     * 返回哈希表 key 中，一个或多个给定域的值。
     * 如果给定的域不存在于哈希表，那么返回一个 nil 值。
     * 因为不存在的 key 被当作一个空哈希表来处理，所以对一个不存在的 key 进行 HMGET
     * 操作将返回一个只带有 nil 值的表
     */
    public void multiGet() {
        stringRedisTemplate.opsForHash().multiGet(key,new ArrayList<>());
    }

    /**
     *hincrby key field increment
     * 为哈希表 key 中的域 field 的值加上增量 increment 。
     * 增量也可以为负数，相当于对给定域进行减法操作。
     * 如果 key 不存在，一个新的哈希表被创建并执行 HINCRBY 命令。
     * 如果域 field 不存在，那么在执行命令前，域的值被初始化为 0 。
     * 对一个储存字符串值的域 field 执行 HINCRBY 命令将造成一个错误。
     * 本操作的值被限制在 64 位(bit)有符号数字表示之内
     */
    public void increment() {
        stringRedisTemplate.opsForHash().increment(key,value,1);

    }

    /**
     * 返回查询键关联的值的长度，为null则返回0【hstrlen】
     */
    public void lengthOfValue() {
        stringRedisTemplate.opsForHash().lengthOfValue(key,"子健");
    }

    /**
     * 获取hashKey的个数【hlen】
     */
    public void size() {
        stringRedisTemplate.opsForHash().size(key);
    }

    public void put() {
        stringRedisTemplate.opsForHash().put(key,"子健",value);
    }

    /**
     * 仅当hashKey不存在时设置值
     */
    public void putIfAbsent() {
        stringRedisTemplate.opsForHash().putIfAbsent(key,"子健",value);
    }

    /**
     * 返回哈希表 key 中所有域的值
     */
    public void values() {
        stringRedisTemplate.opsForHash().values(key);
    }

    /**
     * hgetall key
     * 返回哈希表 key 中，所有的域和值。
     * 在返回值里，紧跟每个域名(field name)之后是域的值(value)，所以返回值的长度是
     * 哈希表大小的两倍。
     */
    public void entries() {
        stringRedisTemplate.opsForHash().entries(key);
    }

    public void scan() {
        String k = "phoneMap";
        String hashKey = "19199999999";
        HashOperations<String, String, String> hash = stringRedisTemplate.opsForHash();
        hash.increment(k,hashKey,1);

        Set set = hash.keys(k);

        Long sizeLong = hash.size(k);


        Cursor<Map.Entry<String, String>> cursor = hash.scan(k, ScanOptions.scanOptions().count(1000).build());
        while (cursor.hasNext()){
            Map.Entry<String, String> entry = cursor.next();
        }
        try {
            if (!cursor.isClosed()){
                cursor.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



}
