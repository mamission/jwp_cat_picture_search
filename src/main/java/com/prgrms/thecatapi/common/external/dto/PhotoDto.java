package com.prgrms.thecatapi.common.external.dto;

public record PhotoDto(
	String id,
	String url,
	int width,
	int height
) {
}
