package com.example.demoredis.test;

import org.redisson.Redisson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;

/**
 * @author liyuan
 * @create 2019-12-30-10:46
 */
public class SetTest {
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
     * sadd key member [member ...]
     * 将一个或多个 member 元素加入到集合 key 当中，已经存在于集合的 member 元素将
     * 被忽略。
     */
    public void add() {
        stringRedisTemplate.opsForSet().add(key,"1","2");
    }

    /**
     * srem key member [member ...]
     * 移除集合 key 中的一个或多个 member 元素，不存在的 member 元素会被忽略。
     */
    public void remove() {
        stringRedisTemplate.opsForSet().remove(key,"1","2");
    }

    /**
     * smembers key
     * 返回集合 key 中的所有成员。
     */
    public void members() {
        stringRedisTemplate.opsForSet().members(key);
    }

    /**
     * sismember key member
     * 判断 member 元素是否集合 key 的成员。
     */
    public void isMember() {
        stringRedisTemplate.opsForSet().isMember(key,1);
    }

    /**
     * scard key
     * 返回集合 key 的基数(集合中元素的数量)。
     */
    public void size() {
        stringRedisTemplate.opsForSet().size(key);
    }

    /**
     * smove source destination member 将 member 元素从 source 集合移动到 destination 集合。
     * SMOVE 是原子性操作。
     * 如果 source 集合不存在或不包含指定的 member 元素，则 SMOVE 命令不执行任何操
     * 作，仅返回 0 。否则， member 元素从 source 集合中被移除，并添加到 destination 集
     * 合中去。
     * 当 destination 集合已经包含 member 元素时，SMOVE 命令只是简单地将 source 集
     * 合中的 member 元素删除。
     */
    public void move() {
        stringRedisTemplate.opsForSet().move(key,"新的set",value);
    }

    /**
     * 移除并返回集合中的2个随机元素,默认一个
     */
    public void pop() {
        stringRedisTemplate.opsForSet().pop(key,2);
    }

    /**
     * 如果命令执行时，只提供了 key 参数，那么返回集合中的一个随机元素。
     */
    public void randomMembers() {
        stringRedisTemplate.opsForSet().randomMembers(key,2);
    }

    /**
     * sinter key [key ...]
     * 返回一个集合的全部成员，该集合是所有给定集合的交集。
     * 不存在的 key 被视为空集。
     * 当给定集合当中有一个空集时，结果也为空集(根据集合运算定律)。
     */
    public void intersect() {
        stringRedisTemplate.opsForSet().intersect(key,new ArrayList<>());
    }

    /**
     * sinterstore destination key [key ...]
     * 这个命令类似于 SINTER 命令，但它将结果保存到 destination 集合，而不是简单地
     * 返回结果集。
     * 如果 destination 集合已经存在，则将其覆盖。
     * destination 可以是 key 本身。
     */
    public void intersectAndStore() {
        stringRedisTemplate.opsForSet().intersectAndStore(key,new ArrayList<>(),"key");
    }

    /**
     * sdiff key [key ...]
     * 返回一个集合的全部成员，该集合是所有给定集合之间的差集。
     * 不存在的 key 被视为空集。
     */
    public void difference() {
        stringRedisTemplate.opsForSet().difference(key,new ArrayList<>());
    }

    /**
     * sdiffstore destination key [key ...]
     * 这个命令的作用和 SDIFF 类似，但它将结果保存到 destination 集合，而不是简单地
     * 返回结果集。
     * 如果 destination 集合已经存在，则将其覆盖。
     * destination 可以是 key 本身
     */
    public void differenceAndStore() {
        stringRedisTemplate.opsForSet().differenceAndStore(key,new ArrayList<>(),"key");
    }

    /**
     * sunion key [key ...]
     * 返回一个集合的全部成员，该集合是所有给定集合的并集。
     * 不存在的 key 被视为空集。
     */
    public void union() {
        stringRedisTemplate.opsForSet().union(key,new ArrayList<>());
    }

    /**
     * sunionstore destination key [key ...]
     * 这个命令类似于 SUNION 命令，但它将结果保存到 destination 集合，而不是简单地
     * 返回结果集。
     * 如果 destination 已经存在，则将其覆盖。
     * destination 可以是 key 本身。
     */
    public void unionAndStore() {
        stringRedisTemplate.opsForSet().unionAndStore(key,new ArrayList<>(),"key");
    }

}
