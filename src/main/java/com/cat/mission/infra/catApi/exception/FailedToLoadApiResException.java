package com.cat.mission.infra.catApi.exception;

import com.cat.mission.global.ErrorCode;

public class FailedToLoadApiResException extends RuntimeException {

  public FailedToLoadApiResException(ErrorCode errorCode) {
    super(errorCode.getMessage());
  }
}
