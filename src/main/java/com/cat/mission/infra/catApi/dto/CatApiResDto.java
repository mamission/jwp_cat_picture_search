package com.cat.mission.infra.catApi.dto;

import com.cat.mission.catInfo.entity.CatInfo;
import com.cat.mission.catInfo.entity.data.Image;
import java.util.List;

public record CatApiResDto(
    List<CatApiBreedsResDto> apiBreedRes,
    String id,
    String url,
    int width,
    int height
) {

  private static final int FIRST_IDX = 0;

  public CatInfo toCatInfo() {
    return new CatInfo(
        id,
        new Image(url, width, height),
        apiBreedRes.get(FIRST_IDX).toBreeds());
  }

}
