package com.geonwoo.thecatapi.feign.config;

import java.util.concurrent.TimeUnit;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.geonwoo.thecatapi.global.properties.ApiKeyProperties;

import feign.Logger;
import feign.Request;
import feign.RequestInterceptor;
import feign.Retryer;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableFeignClients("com.geonwoo.thecatapi")
@RequiredArgsConstructor
public class OpenFeignConfig {

	public final ApiKeyProperties apiKeyProperties;

	@Bean
	public Request.Options requestOptions() {
		return new Request.Options(5000, TimeUnit.MILLISECONDS, 5000, TimeUnit.MILLISECONDS, false);
	}

	@Bean
	public Retryer.Default retryerDefault() {
		return new Retryer.Default(100, TimeUnit.SECONDS.toMillis(5), 10);
	}

	@Bean
	public Logger.Level loggerLevel() {
		return Logger.Level.FULL;
	}

	@Bean
	public RequestInterceptor requestInterceptor() {
		return requestTemplate -> requestTemplate.header("x-api-key", apiKeyProperties.key());
	}
}
