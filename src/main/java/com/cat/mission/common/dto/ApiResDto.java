package com.cat.mission.common.dto;

import java.util.List;

public record ApiResDto<T>(
    T data
) {

  public record ApiListResDto<T>(
      List<T> data
  ) {

  }
}
