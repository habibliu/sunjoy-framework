package com.sunjoy.framework.service.core.config;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@EnableWebMvc
@ConditionalOnProperty(prefix = "api.swagger", value = "title")
public class SwaggerConfig {
	private static final Logger logger = LoggerFactory
			.getLogger(SwaggerConfig.class);
	@SuppressWarnings("unchecked")
	@Bean
	@Lazy
	public Docket createRestApi(@Value("${api.swagger.title}") String title,
			@Value("${api.swagger.contact.name}") String contactName,
			@Value("${api.swagger.contact.url}") String contactUrl,
			@Value("${api.swagger.contact.mail}") String contactMail,
			@Value("${api.swagger.version}") String version,
			@Value("${api.swagger.basePackage}") String basePackage) {
		Contact contact=new Contact(contactName,contactUrl,contactMail);
		logger.debug("SwaggerConfig is initializing......");
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo(title, contact, version)).select()
				.apis(RequestHandlerSelectors.basePackage(basePackage)).paths(or(regex("/*/.*"))).build();
	}

	private ApiInfo apiInfo(String title, Contact contact, String version) {
		@SuppressWarnings("deprecation")
		ApiInfo apiInfo = new ApiInfoBuilder().title(title).contact(contact).version(version).build();

		return apiInfo;
	}
}
