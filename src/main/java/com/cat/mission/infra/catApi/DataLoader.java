package com.cat.mission.infra.catApi;

import com.cat.mission.catBreeds.repository.CatBreedsRepository;
import com.cat.mission.catImage.repository.CatImageRepository;
import com.cat.mission.global.ErrorCode;
import com.cat.mission.infra.catApi.dto.ApiResponseDto;
import com.cat.mission.infra.catApi.exception.FailedToLoadImagesException;
import jakarta.annotation.PostConstruct;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DataLoader {

  private final CatApiService catApiService;
  private final CatImageRepository catImageRepository;
  private final CatBreedsRepository catBreedsRepository;
  private static final int LIMIT = 50;
  private static final boolean HAS_BREEDS = true;

  public DataLoader(CatApiService catApiService, CatImageRepository catImageRepository,
      CatBreedsRepository catBreedsRepository) {
    this.catApiService = catApiService;
    this.catImageRepository = catImageRepository;
    this.catBreedsRepository = catBreedsRepository;
  }

  @Transactional
  @PostConstruct
  public void dataLoad() {

    if (catBreedsRepository.count() < 50) {
      List<ApiResponseDto> randomCatImages;

      try {
        randomCatImages = catApiService.getRandomCatImages(LIMIT, HAS_BREEDS);
      } catch (Exception e) {
        throw new FailedToLoadImagesException(ErrorCode.CANNOT_LOAD_DATER);
      }
//      catImageRepository.saveAll(randomCatImages.stream().map(ApiResponseDto::toCatImage).toList());
      for (ApiResponseDto apiResponseDto : randomCatImages) {
        catBreedsRepository.saveAll(apiResponseDto.toBreeds(apiResponseDto.toCatImage()));
      }

    }
  }

}
