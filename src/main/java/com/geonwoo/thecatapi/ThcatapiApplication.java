package com.geonwoo.thecatapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class ThcatapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThcatapiApplication.class, args);
	}

}
