package com.prgrms.thecatapi.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Contract;
import feign.RequestInterceptor;

@Configuration
@EnableFeignClients("com.prgrms.thecatapi")
class OpenFeignConfig {

	@Value("${cat.api-key}")
	private String apiKey;

	@Bean
	public RequestInterceptor requestInterceptor() {
		return template -> {
			template.header("x-api-key", apiKey);
			template.header("Accept", "application/json");
		};
	}

	@Bean
	public Contract useFeignAnnotations() {
		return new Contract.Default();
	}
}