package com.langchao.waveservicesearch.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@ComponentScan("com.liyuan.waveserviceapi.config")
public class SwaggerConfiguration{

}