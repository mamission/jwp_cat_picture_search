package com.cat.picture_search.global.error;

import org.springframework.http.HttpStatus;

public record ErrorResponse(
	HttpStatus httpStatus,
	String message
) {

	public static ErrorResponse of(HttpStatus httpStatus, String message) {
		return new ErrorResponse(httpStatus, message);
	}
}
