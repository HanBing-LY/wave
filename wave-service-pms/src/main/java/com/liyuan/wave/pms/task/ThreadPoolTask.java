package com.liyuan.wave.pms.task;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author liyuan
 * @description
 * @email 724837404@qq.com
 * @date 2020-06-18-23:43
 */
public class ThreadPoolTask {

    public static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 20, 10, TimeUnit.SECONDS, new LinkedBlockingDeque<>(100000), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
}
