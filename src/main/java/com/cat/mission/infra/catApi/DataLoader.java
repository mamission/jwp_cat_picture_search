package com.cat.mission.infra.catApi;

import com.cat.mission.catInfo.repository.CatInfoRepository;
import com.cat.mission.global.error.ErrorCode;
import com.cat.mission.infra.catApi.dto.CatApiResDto;
import com.cat.mission.infra.catApi.exception.FailedToLoadApiResException;
import jakarta.annotation.PostConstruct;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DataLoader {

  private final CatApiService catApiService;
  private final CatInfoRepository catInfoRepository;
  private static final int LIMIT = 50;
  private static final boolean HAS_BREEDS = true;

  public DataLoader(CatApiService catApiService, CatInfoRepository catInfoRepository) {
    this.catApiService = catApiService;
    this.catInfoRepository = catInfoRepository;
  }

  @Transactional
  @PostConstruct
  public void dataLoad() {

    if (catInfoRepository.count() < LIMIT) {
      List<CatApiResDto> randomCatImages;

      try {
        randomCatImages = catApiService.getRandomCatImages(LIMIT, HAS_BREEDS);
      } catch (Exception e) {
        throw new FailedToLoadApiResException(ErrorCode.CANNOT_LOAD_DATER);
      }
      catInfoRepository.saveAll(randomCatImages.stream()
          .map(CatApiResDto::toCatInfo)
          .toList());
    }
  }

}
