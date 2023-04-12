package com.api.mission.cat.exception;

import com.api.mission.global.error.ErrorCode;
import org.springframework.http.HttpStatus;

public class CatImageNotFoundException extends RuntimeException {

  private final ErrorCode errorCode;

  public CatImageNotFoundException(ErrorCode errorCode) {
    this.errorCode = errorCode;
  }

  public HttpStatus getHttpStatusCode() {
    return errorCode.getHttpStatus();
  }
}
