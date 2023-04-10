package com.cat.mission.catInfo.repository.querydsl;

import com.cat.mission.catInfo.dto.CatInfoSimpleResDto;
import java.util.List;

public interface CatInfoRepositoryQuerydsl {

  List<CatInfoSimpleResDto> getRandomImages(int limit);

}
