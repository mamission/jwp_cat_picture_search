package com.cat.mission.infra.catApi.config;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class HeaderConfiguration {

  @Bean
  public RequestInterceptor requestInterceptor(@Value("${cat-api.key}") String apiKey) {
    return requestTemplate -> requestTemplate.header("x-api-key", apiKey);
  }
}
