package com.geonwoo.thecatapi.global.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.geonwoo.thecatapi.domain.catImage.exception.CatImageNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(CatImageNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleCatNotFoundException(CatImageNotFoundException e) {

		return ResponseEntity.badRequest().body(ErrorResponse.of(e.getMessage()));
	}
}
