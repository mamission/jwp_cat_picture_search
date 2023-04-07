package com.cat.picture_search.global.catApi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cat.picture_search.global.error.Error;
import com.cat.picture_search.global.error.ErrorResponse;

import feign.FeignException;

@RestControllerAdvice
public class CatPictureOpenFeignHandler {

	private final Logger logger = LoggerFactory.getLogger(CatPictureOpenFeignHandler.class);

	@ExceptionHandler(FeignException.class)
	public ResponseEntity<ErrorResponse> handelFeignException(FeignException feignException) {
		logger.info(feignException.getMessage(), feignException);

		ErrorResponse response = ErrorResponse.of(
			Error.CAN_NOT_GET_IMAGE.getHttpStatus(),
			Error.CAN_NOT_GET_IMAGE.getMessage()
		);

		return ResponseEntity.status(response.httpStatus()).body(response);
	}
}
