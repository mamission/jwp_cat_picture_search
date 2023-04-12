package com.api.mission.infra.catApi.exception;

import com.api.mission.global.error.ErrorCode;
import org.springframework.http.HttpStatus;

public class FailedToLoadApiResException extends RuntimeException {

  private final ErrorCode errorCode;

  public FailedToLoadApiResException(ErrorCode errorCode) {
    this.errorCode = errorCode;
  }

  public HttpStatus getHttpStatusCode() {
    return errorCode.getHttpStatus();
  }

}
