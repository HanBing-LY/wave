package com.liyuan.wave.wms.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author liyuan
 * @description 扫描全局异常处理器
 * @email 724837404@qq.com
 * @date 2020-06-21-20:52
 */
@Configuration
@ComponentScan("com.liyuan.wave.common.exception")
public class ExceptionConfiguration {
}
