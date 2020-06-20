package com.example.demoredis.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author liyuan
 * @description
 * @date 2020-06-20 17:08
 */
@Component
public class RedisUtil {
    @Autowired
    private static StringRedisTemplate stringRedisTemplate;

    /**
     * @param key
     * @return
     * @description 提供了对key的“bound”(绑定)便捷化操作API，可以通过bound封装指定的key，然后进行一系列的操作而无须“显式”的再次指定Key，即BoundKeyOperations
     * 我对一个key要进行多次操作,不需要多次代码进行指定这个key了
     */
    public static BoundValueOperations<String, String> boundValueOps(String key) {
        return stringRedisTemplate.boundValueOps(key);
    }

    /**
     * @param key
     * @return 是否删除成功
     * @description 删除key
     */
    public static Boolean delete(String key) {
        return stringRedisTemplate.delete(key);
    }

    /**
     * @param keys key集合
     * @return 被删除的个数
     * @description 批量删除key
     */
    public static Long delete(Collection<String> keys) {
        return stringRedisTemplate.delete(keys);
    }

    /**
     * @param key
     * @return
     * @description 序列化key
     */
    public static byte[] dump(String key) {
        return stringRedisTemplate.dump(key);
    }

    /**
     * @param key
     * @return
     * @description 是否存在key
     */
    public static Boolean hasKey(String key) {
        return stringRedisTemplate.hasKey(key);
    }

    /**
     * @param key     需要设置过期时间的键
     * @param timeout 距离多久过期
     * @param unit    时间单位
     * @return 是否设置成功
     * @description 设置过期时间
     */
    public static Boolean expire(String key, long timeout, TimeUnit unit) {
        return stringRedisTemplate.expire(key, timeout, unit);
    }

    /**
     * @description 设置过期时间,指定过期时间
     * @param key 需要设置过期时间的键
     * @param date 过期的时间
     * @return 是否设置成功
     */
    public static Boolean expireAt(String key, Date date) {
        return stringRedisTemplate.expireAt(key, date);
    }

    /**
     * @description 查找匹配的key
     * @param pattern
     * @return
     */
    public static Set<String> keys(String pattern) {
        return stringRedisTemplate.keys(pattern);
    }

    /**
     * @description 将当前数据库的 key 移动到给定的数据库 db 当中
     * @param key
     * @param dbIndex 目标数据库
     * @return
     */
    public static Boolean move(String key, int dbIndex) {
        return stringRedisTemplate.move(key, dbIndex);
    }

    /**
     * @description 移除 key 的过期时间，key 将持久保持
     * @param key
     * @return
     */
    public static Boolean persist(String key) {
        return stringRedisTemplate.persist(key);
    }

    /**
     * @description 返回 key 的剩余的过期时间
     * @param key
     * @param unit 时间单位
     * @return
     */
    public static Long getExpire(String key, TimeUnit unit) {
        return stringRedisTemplate.getExpire(key, unit);
    }

    /**
     * @description 返回 key 的剩余的过期时间
     * @param key
     * @return
     */
    public static Long getExpire(String key) {
        return stringRedisTemplate.getExpire(key);
    }

    /**
     * @description 从当前数据库中随机返回一个 key
     * @return
     */
    public static String randomKey() {
        return stringRedisTemplate.randomKey();
    }

    /**
     * @description 修改 key 的名称
     * @param oldKey 旧名字
     * @param newKey 新名字
     */
    public static void rename(String oldKey, String newKey) {
        stringRedisTemplate.rename(oldKey, newKey);
    }

    /**
     * @description 仅当 newkey 不存在时，将 oldKey 改名为 newkey
     * @param oldKey
     * @param newKey
     * @return
     */
    public static Boolean renameIfAbsent(String oldKey, String newKey) {
        return stringRedisTemplate.renameIfAbsent(oldKey, newKey);
    }

    /**
     * @description 返回 key 所储存的值的类型
     * @param key
     * @return
     */
    public static DataType type(String key) {
        return stringRedisTemplate.type(key);
    }
}
