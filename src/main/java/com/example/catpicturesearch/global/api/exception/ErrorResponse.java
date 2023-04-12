package com.example.catpicturesearch.global.api.exception;

import org.springframework.http.ResponseEntity;

import lombok.Getter;

@Getter
public class ErrorResponse {

	private final String code;

	private final String message;

	private ErrorResponse(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public static ResponseEntity<ErrorResponse> error(String code, String message) {
		return ResponseEntity.internalServerError()
				.body(new ErrorResponse(code, message));
	}

}
