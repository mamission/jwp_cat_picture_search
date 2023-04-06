package com.example.catpicture.infra.feign;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients("com.example.catpicture.infra.feign")
public class FeignConfig {
}
