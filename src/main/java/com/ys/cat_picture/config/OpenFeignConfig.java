package com.ys.cat_picture.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignFormatterRegistrar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ys.cat_picture.infra.client.FeignErrorDecoder;

import feign.Logger;
import feign.codec.ErrorDecoder;

@EnableFeignClients("com.ys.cat_picture")
@Configuration
public class OpenFeignConfig {

	@Bean
	Logger.Level feignLoggerLevel() {
		return Logger.Level.FULL;
	}

	@Bean
	public FeignFormatterRegistrar dateTimeFormatterRegistrar() {
		return registry -> {
			DateTimeFormatterRegistrar registrar = new DateTimeFormatterRegistrar();
			registrar.setUseIsoFormat(true);
			registrar.registerFormatters(registry);
		};
	}

	@Bean
	public ErrorDecoder errorDecoder(ObjectMapper objectMapper) {
		return new FeignErrorDecoder(objectMapper);
	}

}
