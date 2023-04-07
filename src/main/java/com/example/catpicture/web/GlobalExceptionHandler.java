package com.example.catpicture.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.catpicture.domain.CatPictureNotFoundException;
import com.example.catpicture.web.error.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException e) {
		return ResponseEntity.internalServerError()
			.body(new ErrorResponse(e.getMessage()));
	}

	@ExceptionHandler(CatPictureNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleCatPictureNotFoundException(CatPictureNotFoundException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
			.body(new ErrorResponse(e.getMessage()));
	}
}
