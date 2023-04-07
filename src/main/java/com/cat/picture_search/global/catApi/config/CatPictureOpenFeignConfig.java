package com.cat.picture_search.global.catApi.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Logger;

@Configuration
@EnableFeignClients("com.cat.picture_search.global.catApi")
public class CatPictureOpenFeignConfig {

	@Bean
	Logger.Level loggerLevel() {
		return Logger.Level.FULL;
	}
}
