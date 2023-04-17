package com.prgrms.thecatapi.common.exception;

public class PhotoNotFoundException extends RuntimeException {
	public PhotoNotFoundException() {
		super();
	}

	public PhotoNotFoundException(String message) {
		super(message);
	}
}
