package com.pfk.thecats.infra.thecatapi;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Logger;

@Configuration
@EnableFeignClients("com.pfk.thecats")
public class CatOpenFeignConfig {

	@Bean
	Logger.Level feignLoggerLevel() {
		return Logger.Level.FULL;
	}
}
