package com.ys.cat_picture.common.error;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ys.cat_picture.common.dto.ErrorResponse;

import feign.FeignException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(FeignException.class)
	public ResponseEntity<ErrorResponse> handleBusinessException(
		HttpServletRequest request, FeignException e) {
		logWarn(e, request.getRequestURI());
		return ResponseEntity.status(e.status())
			.body(new ErrorResponse(e.status(), e.getMessage(), LocalDateTime.now(), request.getRequestURI()));
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ErrorResponse> handleRuntimeException(
		HttpServletRequest request, RuntimeException e) {
		logError(e, request.getRequestURI());
		return ResponseEntity.badRequest()
			.body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(),
				e.getMessage(), LocalDateTime.now(), request.getRequestURI()));
	}

	private void logWarn(Exception e, String path) {
		log.warn("path : {}, Exception Name : {}, Message : {}", path, e.getClass().getSimpleName(), e.getMessage());
	}

	private void logError(Exception e, String path) {
		log.error("path : {}, Exception Name : {}, Message : {}", path, e.getClass().getSimpleName(), e.getMessage());
	}

}
