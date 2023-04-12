package com.api.mission;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class CatApplication {

  public static void main(String[] args) {
    SpringApplication.run(CatApplication.class, args);
  }

}
