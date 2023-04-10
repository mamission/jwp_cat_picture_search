package com.api.mission.infra.catApi.dto;

import com.api.mission.cat.entity.Cat;
import com.api.mission.cat.entity.data.Breeds;
import com.api.mission.cat.entity.data.Image;
import com.api.mission.cat.entity.data.Weight;
import java.util.List;

public record CatApiResDto(
    List<CatApiBreedsResDto> apiBreedRes,
    String id,
    String url,
    int width,
    int height
) {

  private static final int FIRST_IDX = 0;
  private static final String EMPTY = "";
  private static final Breeds tempBreeds =
      new Breeds(EMPTY,EMPTY,EMPTY,EMPTY,new Weight(EMPTY, EMPTY));

  public Cat toCatInfo() {

    return new Cat(
        id,
        new Image(url, width, height),
        apiBreedRes == null ? tempBreeds : apiBreedRes.get(FIRST_IDX).toBreeds());
  }

}
