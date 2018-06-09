package com.sunjoy.framework.dao.datasource.druid;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sunjoy.framework.dao.interceptor.DialectHelper;
import com.sunjoy.framework.dao.interceptor.PaginationInterceptor;
import com.sunjoy.framework.dao.interceptor.PostgreSqlHelper;

/**
 * 分页拦截器插件配置
 */
@Configuration
@MapperScan(basePackages = { "com.sunjoy" })
public class MybatisPlusConfig {
	@Bean
	public PaginationInterceptor paginationInterceptor() {
		PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
		DialectHelper dialectHelper = new PostgreSqlHelper();
		paginationInterceptor.setDialectHelper(dialectHelper);
		return paginationInterceptor;
	}
}