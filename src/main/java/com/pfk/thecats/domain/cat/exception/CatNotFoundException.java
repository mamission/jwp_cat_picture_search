package com.pfk.thecats.domain.cat.exception;

public class CatNotFoundException extends RuntimeException {

	public CatNotFoundException(String message) {
		super(message);
	}
}
