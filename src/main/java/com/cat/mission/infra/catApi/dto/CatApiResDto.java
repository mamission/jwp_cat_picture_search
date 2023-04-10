package com.cat.mission.infra.catApi.dto;

import com.cat.mission.catInfo.entity.CatInfo;
import com.cat.mission.catInfo.entity.data.Breeds;
import com.cat.mission.catInfo.entity.data.Image;
import com.cat.mission.catInfo.entity.data.Weight;
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

  public CatInfo toCatInfo() {

    return new CatInfo(
        id,
        new Image(url, width, height),
        apiBreedRes == null ? tempBreeds : apiBreedRes.get(FIRST_IDX).toBreeds());
  }

}
