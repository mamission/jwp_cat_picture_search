package com.search.cat_picture.global.exception;

public class BadRequestException extends RuntimeException{

	@Override
	public String getMessage() {
		return "잘못된 요청값 입니다.";
	}
}
