package com.cat.mission.infra.catApi.exception;

import com.cat.mission.global.error.ErrorCode;
import org.springframework.http.HttpStatus;

public class FailedToLoadApiResException extends RuntimeException {

  private final ErrorCode errorCode;

  public FailedToLoadApiResException(ErrorCode errorCode) {
    this.errorCode = errorCode;
  }

  public HttpStatus getErrorCode() {
    return errorCode.getHttpStatus();
  }

}
