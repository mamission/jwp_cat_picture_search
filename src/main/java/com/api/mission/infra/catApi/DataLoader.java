package com.api.mission.infra.catApi;

import com.api.mission.cat.repository.CatRepository;
import com.api.mission.global.error.ErrorCode;
import com.api.mission.infra.catApi.dto.CatApiResDto;
import com.api.mission.infra.catApi.exception.FailedToLoadApiResException;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DataLoader implements CommandLineRunner {

  private final CatApiService catApiService;
  private final CatRepository catInfoRepository;
  private static final int LIMIT = 50;
  private static final boolean HAS_BREEDS = true;

  public DataLoader(CatApiService catApiService, CatRepository catInfoRepository) {
    this.catApiService = catApiService;
    this.catInfoRepository = catInfoRepository;
  }


  @Transactional
  @Override
  public void run(String... args) {
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
