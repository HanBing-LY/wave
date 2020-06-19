package com.liyuan.wave.task.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author liyuan
 * @description 商品通用缓存,商品总库存,分类相关商品信息,统计日统一商品信息
 * @date 2020-06-19 12:45
 */
public class ProductTask {

    /**
     * @description 每隔半小时统计各商品总库存  1000*60*30 = 1800000
     * initialDelay 定时器第一次执行延迟多久开启 (毫秒制)
     * fixedRate 上次任务执行开始的时候开始计时 (毫秒制)
     * fixedDelay 等待上次任务结束开始计时 (毫秒制)
     */
    @Scheduled(initialDelay = 0, fixedDelay = 1800000)
    public void countEveryProductAllStock(){

    }
}
