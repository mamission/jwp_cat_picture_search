package com.cat.picture_search.api.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Logger;

@Configuration
@EnableFeignClients("com.cat.picture_search.api")
public class CatPictureOpenFeignConfig {

	@Bean
	Logger.Level loggerLevel() {
		return Logger.Level.FULL;
	}
}
