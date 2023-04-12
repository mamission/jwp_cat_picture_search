package com.example.catpicturesearch.global.api.exception;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = { RuntimeException.class })
	public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException e) {
		log.warn("{} : {}", LocalDateTime.now(), e.getMessage());

		// TODO : code, message를 ENUM으로 변환해 Application Code 에서 특정한 Code를 뱉을 수 있도록 구현 예정 (ex. E001)
		return ErrorResponse.error("500", e.getMessage());
	}

	@ExceptionHandler(value = { Exception.class })
	public ResponseEntity<ErrorResponse> handleRuntimeException(Exception e) {
		log.error("{} : {}", LocalDateTime.now(), e.getMessage());

		// TODO : 위와 마찬가지
		return ErrorResponse.error("500", e.getMessage());
	}

}
