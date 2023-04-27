package com.search.cat_picture.global.exception;

public class ResourceNotFoundException extends RuntimeException {

	private final String message;

	public ResourceNotFoundException() {
		this.message = "해당 리소스를 찾을 수 없습니다.";
	}

	@Override
	public String getMessage() {
		return message;
	}
}
