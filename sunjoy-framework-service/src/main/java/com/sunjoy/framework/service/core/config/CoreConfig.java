package com.sunjoy.framework.service.core.config;

import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
//@EnableHystrix
@EnableFeignClients(basePackages = { "com.sunjoy.**.feign.**",
		"com.sunjoy.**.api.**" })
@Import({ SwaggerConfig.class })
@ComponentScan(basePackages = { "com.sunjoy" })
public class CoreConfig {

}
