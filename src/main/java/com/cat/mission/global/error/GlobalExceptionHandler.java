package com.cat.mission.global.error;

import com.cat.mission.infra.catApi.exception.FailedToLoadApiResException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  @ExceptionHandler(FailedToLoadApiResException.class)
  public ResponseEntity<ErrorRes> handleBusinessException(FailedToLoadApiResException e) {
    log.info("FailedToLoadApiResException: ", e);
    return ResponseEntity.status(e.getErrorCode())
        .body(new ErrorRes(e.getErrorCode(), e.getMessage()));
  }

  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<String> handleRuntimeException(RuntimeException e) {
    log.error("RuntimeException : ", e);
    return ResponseEntity.internalServerError().build();
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<String> handleException(Exception e) {
    log.error("Exception : ", e);
    return ResponseEntity.internalServerError().build();
  }

}
