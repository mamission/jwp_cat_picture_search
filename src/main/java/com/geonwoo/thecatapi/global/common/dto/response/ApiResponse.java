package com.geonwoo.thecatapi.global.common.dto.response;

public record ApiResponse<T>(
	T data
) {
}
