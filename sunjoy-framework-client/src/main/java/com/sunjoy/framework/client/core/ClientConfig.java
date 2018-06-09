package com.sunjoy.framework.client.core;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * Client Configuration
 */
@Configuration
@ComponentScan(basePackages = { "com.sunjoy" })
public class ClientConfig {
	 
	 /**
	 * Get Rest Template
	 */
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplateBuilder()
				.additionalMessageConverters(
						new MappingJackson2HttpMessageConverter())
				.additionalMessageConverters(new FormHttpMessageConverter())
				.build();
	}
}
