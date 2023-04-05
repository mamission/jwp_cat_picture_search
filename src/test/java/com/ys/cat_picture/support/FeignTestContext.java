package com.ys.cat_picture.support;

import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.http.HttpMessageConvertersAutoConfiguration;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;

import com.ys.cat_picture.config.OpenFeignConfig;

@ImportAutoConfiguration({
	OpenFeignConfig.class,
	FeignAutoConfiguration.class,
	HttpMessageConvertersAutoConfiguration.class,
})
public class FeignTestContext {
}
