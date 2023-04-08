package com.cat.mission.infra.catApi.dto;

import com.cat.mission.catBreeds.entity.Breeds;
import com.cat.mission.catImage.entity.CatImage;
import java.util.List;

public record ApiResponseDto(
    List<ApiBreedsResDto> breeds,
    String id,
    String url,
    int width,
    int height
) {

  public CatImage toCatImage() {
    return new CatImage(
        id,
        url,
        width,
        height
    );
  }

  public List<Breeds> toBreeds(CatImage catImage) {
    return breeds.stream()
        .map(apiBreedsResDto -> apiBreedsResDto.toBreeds(catImage))
        .toList();
  }
}
