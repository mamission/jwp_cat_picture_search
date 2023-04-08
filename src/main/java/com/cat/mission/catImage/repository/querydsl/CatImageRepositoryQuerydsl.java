package com.cat.mission.catImage.repository.querydsl;

import com.cat.mission.catImage.dto.CatImageDetailResDto;
import com.cat.mission.catImage.dto.CatImageResDto;
import java.util.List;

public interface CatImageRepositoryQuerydsl {

  List<CatImageResDto> getRandomImages(int limit);
  List<CatImageResDto> getSearchListBy(String breedsName);

  CatImageDetailResDto getByDisplayId(String id);
}
