package com.geonwoo.thecatapi.global.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "the-cat-api")
public record ApiKeyProperties(
	String key
) {
}
