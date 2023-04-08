package com.cat.mission.infra.catApi.exception;

import com.cat.mission.global.ErrorCode;

public class FailedToLoadImagesException extends RuntimeException {

  public FailedToLoadImagesException(ErrorCode errorCode) {
    super(errorCode.getMessage());
  }
}
