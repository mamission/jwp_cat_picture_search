package com.search.cat_picture.global.openFeign;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Logger;

@Configuration
@EnableFeignClients("com.search.cat_picture")
public class OpenFeignConfig {

	@Bean
	Logger.Level feignLoggerLevel(){
		return Logger.Level.BASIC;
	}

	@Bean
	public FeignErrorDecider commonFeignErrorDecoder() {
		return new FeignErrorDecider();
	}
}
