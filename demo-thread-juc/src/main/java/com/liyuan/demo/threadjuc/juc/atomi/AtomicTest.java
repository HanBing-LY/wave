package com.liyuan.demo.threadjuc.juc.atomi;

/**
 * @author liyuan
 * @description i ++ 原子性问题:  分为读改写
 * int i = 10;
 * i ++;
 * int temp = i;
 * i = i + 1;
 * i = temp;
 *
 * com.liyuan.wave.juc.atomic 包下提供了常用的原子变量
 * 1.volatile 保证内存可见性
 * 2.CAS( compare - and - swap)算法保证数据的原子性
 *      cas算法是硬件对于并发操作共享数据的支持
 *      cas包含三个操作:
 *      内存值v
 *      预估值a
 *      更新值b
 *      当且仅当 v=a时,v=b
 * @date 2020-06-14-14:24
 */
public class AtomicTest {

    public static void main(String[] args) {
        AtomicDemo ad = new AtomicDemo();

        for (int i = 0; i < 10; i++) {
            new Thread(ad).start();
        }
    }

}