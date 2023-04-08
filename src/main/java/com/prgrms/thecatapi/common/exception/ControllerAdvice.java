package com.prgrms.thecatapi.common.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import feign.FeignException;

@RestControllerAdvice
public class ControllerAdvice {

	@ExceptionHandler(PhotoNotFoundException.class)
	public ResponseEntity<ErrorResponse> handlePhotoNotFoundException(PhotoNotFoundException e) {
		return ResponseEntity.badRequest().body(ErrorResponse.of(e.getMessage()));
	}

	@ExceptionHandler(FeignException.class)
	public ResponseEntity<ErrorResponse> handleFeignException(FeignException e) {
		return ResponseEntity.badRequest().body(ErrorResponse.of(e.getMessage()));
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException e) {
		return ResponseEntity.badRequest().body(ErrorResponse.of(e.getMessage()));
	}
}
