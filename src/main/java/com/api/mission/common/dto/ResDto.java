package com.api.mission.common.dto;

import java.util.List;

public record ResDto<T>(
    T data
) {

  public record ListResDto<T>(
      List<T> data
  ) {

  }
}
