package com.ys.cat_picture.infra.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import feign.RequestInterceptor;

public class FeignClientHeaderConfiguration {

	@Bean
	public RequestInterceptor requestInterceptor(@Value("${cat-api.key}")String apiKey) {
		return requestTemplate -> requestTemplate.header("x-api-key", apiKey);
	}

}
