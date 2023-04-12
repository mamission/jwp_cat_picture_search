package com.api.mission.cat.repository.querydsl;

import com.api.mission.cat.dto.CatSimpleResDto;
import java.util.List;

public interface CatRepositoryQuerydsl {

  List<CatSimpleResDto> getRandomImages(int limit);

}
