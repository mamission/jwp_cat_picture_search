package com.example.catpicturesearch.global.api;

import org.springframework.http.ResponseEntity;

import lombok.Getter;

@Getter
public class ApiResponse<T> {

	private final T data;

	private ApiResponse(T data) {
		this.data = data;
	}

	public static <T> ResponseEntity<ApiResponse> ok(T data) {
		return ResponseEntity.ok(new ApiResponse<>(data));
	}
	
}
