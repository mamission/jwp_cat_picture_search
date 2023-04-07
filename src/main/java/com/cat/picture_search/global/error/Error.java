package com.cat.picture_search.global.error;

import org.springframework.http.HttpStatus;

public enum Error {
	CAN_NOT_GET_IMAGE(HttpStatus.BAD_REQUEST, "해당 id에 일치하는 이미지를 찾아올 수 없습니다.");

	private final HttpStatus httpStatus;
	private final String message;

	Error(HttpStatus httpStatus, String message) {
		this.httpStatus = httpStatus;
		this.message = message;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public String getMessage() {
		return message;
	}
}
