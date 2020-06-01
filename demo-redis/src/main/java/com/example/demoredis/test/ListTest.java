package com.example.demoredis.test;

import org.redisson.Redisson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author liyuan
 * @create 2019-12-30-0:16
 */
public class ListTest {

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
     * lrange key start stop
     * 返回列表 key 中指定区间内的元素，区间以偏移量 start 和 stop 指定。
     * 下标(index)参数 start 和 stop 都以 0 为底，也就是说，以 0 表示列表的第一个元
     * 素，以 1 表示列表的第二个元素，以此类推。
     * 你也可以使用负数下标，以 -1 表示列表的最后一个元素， -2 表示列表的倒数第二个
     * 元素，以此类推。
     */
    public void lRange(){
        List<String> stringList = stringRedisTemplate.opsForList().range(key, 1, 10);
        List<String> strings = stringRedisTemplate.opsForList().range(key, -1, -2);
    }

    /**
     * 对一个列表进行修剪(trim)，就是说，让列表只保留指定区间内的元素，不在指定区间
     * 之内的元素都将被删除。
     * 举个例子，执行命令 LTRIM list 0 2 ，表示只保留列表 list 的前三个元素，其余元
     * 素全部删除。
     * 下标(index)参数 start 和 stop 都以 0 为底，也就是说，以 0 表示列表的第一个元
     * 素，以 1 表示列表的第二个元素，以此类推。
     * 你也可以使用负数下标，以 -1 表示列表的最后一个元素， -2 表示列表的倒数第二个
     * 元素，以此类推。
     * 当 key 不是列表类型时，返回一个错误。
     * LTRIM 命令通常和 LPUSH 命令或 RPUSH 命令配合使用，举个例子：
     * LPUSH log newest_log
     * LTRIM log 0 99
     * 这个例子模拟了一个日志程序，每次将最新日志 newest_log 放到 log 列表中，并且
     * 只保留最新的 100 项。注意当这样使用 LTRIM 命令时，时间复杂度是 O(1)，因为平均情
     * 况下，每次只有一个元素被移除。
     * 注意 LTRIM 命令和编程语言区间函数的区别
     * 假如你有一个包含一百个元素的列表 list ，对该列表执行 LTRIM list 0 10 ，结果
     * 是一个包含 11 个元素的列表，这表明 stop 下标也在 LTRIM 命令的取值范围之内(闭区间)，
     * 这和某些语言的区间函数可能不一致，比如 Ruby 的 Range.new 、 Array#slice 和 Python
     * 的 range() 函数。
     * 超出范围的下标
     * 超出范围的下标值不会引起错误。
     * 如果 start 下标比列表的最大下标 end ( LLEN list 减去 1 )还要大，或者 start >
     * stop ， LTRIM 返回一个空列表(因为 LTRIM 已经将整个列表清空)。
     * 如果 stop 下标比 end 下标还要大，Redis 将 stop 的值设置为 end 。
     */
    public void lTrim(){
        stringRedisTemplate.opsForList().trim(key, 1, 10);
    }

    /**
     * llen key
     * 返回列表 key 的长度。
     * 如果 key 不存在，则 key 被解释为一个空列表，返回 0 .
     * 如果 key 不是列表类型，返回一个错误
     */
    public void lSize(){
        stringRedisTemplate.opsForList().size(key);
    }

    public void leftPush(){
        stringRedisTemplate.opsForList().leftPush(key,value);
        // 在pivot（匹配到的第一个）之前（左侧）添加value【linsert】
        stringRedisTemplate.opsForList().leftPush(key,"a",value);
        // 存在就插入
        stringRedisTemplate.opsForList().leftPushIfPresent(key,value);
        stringRedisTemplate.opsForList().leftPushAll(key,new ArrayList<>());

    }

    /**
     * 设置值，有则覆盖，没有则新增【lset】
     */
    public void set(){
        stringRedisTemplate.opsForList().set(key,100,value);
    }

    /**
     * 返回列表 key 中，下标为 index 的元素。
     * 下标(index)参数 start 和 stop 都以 0 为底，也就是说，以 0 表示列表的第一个元
     * 素，以 1 表示列表的第二个元素，以此类推。
     * 你也可以使用负数下标，以 -1 表示列表的最后一个元素， -2 表示列表的倒数第二个
     * 元素，以此类推。
     */
    public void index(){
        stringRedisTemplate.opsForList().index(key,100);
    }

    /**
     * lpop key
     * 移除并返回列表 key 的头元素。
     */
    public void leftPop(){
        stringRedisTemplate.opsForList().leftPop(key);
    }

    /**
     * blpop key [key ...] timeout
     * BLPOP 是列表的阻塞式(blocking)弹出原语。
     * 它是 LPOP 命令的阻塞版本，当给定列表内没有任何元素可供弹出的时候，连接将被
     * BLPOP 命令阻塞，直到等待超时或发现可弹出元素为止。
     * 当给定多个 key 参数时，按参数 key 的先后顺序依次检查各个列表，弹出第一个非空
     * 列表的头元素。
     */
    public void brPop(){
        String brPop = stringRedisTemplate.opsForList().rightPop(key, 60 * 60, TimeUnit.SECONDS);
    }

    /**
     * lrem key count value
     * 根据参数 count 的值，移除列表中与参数 value 相等的元素。
     * count 的值可以是以下几种：
     * count > 0 : 从表头开始向表尾搜索，移除与 value 相等的元素，数量为 count 。
     * count < 0 : 从表尾开始向表头搜索，移除与 value 相等的元素，数量为 count 的绝
     * 对值。
     * count = 0 : 移除表中所有与 value 相等的值
     */
    public void remove(){
        stringRedisTemplate.opsForList().remove(key,0,value);
    }

    /**
     * rpoplpush source destination
     * 安全的队列
     * 循环列表
     * 命令 RPOPLPUSH 在一个原子时间内，执行以下两个动作：
     *  将列表 source 中的最后一个元素(尾元素)弹出，并返回给客户端。
     *  将 source 弹出的元素插入到列表 destination ，作为 destination 列表的的头
     * 元素。
     */
    public void rightPopAndLeftPush(){
        stringRedisTemplate.opsForList().rightPopAndLeftPush(key,"新的队列");
    }

}
