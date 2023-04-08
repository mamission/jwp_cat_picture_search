package com.cat.mission.catImage.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.cat.mission.catBreeds.entity.Breeds;
import com.cat.mission.catBreeds.repository.CatBreedsRepository;
import com.cat.mission.catImage.dto.CatImageDetailResDto;
import com.cat.mission.catImage.dto.CatImageResDto;
import com.cat.mission.catImage.entity.CatImage;
import com.cat.mission.catImage.repository.CatImageRepository;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class CatImageServiceTest {

  @Autowired
  private CatImageService catImageService;

  @Autowired
  private CatImageRepository catImageRepository;
  @Autowired
  private CatBreedsRepository catBreedsRepository;

  @Test
  @DisplayName("랜덤으로 고양이 사진 50개를 가져올 수 있다")
  void get_50_random_images() {
    List<CatImageResDto> randomImages = catImageService.getRandomImages();

    assertThat(randomImages.size()).isEqualTo(50);
  }

  @Test
  @DisplayName("고양이 품종이름으로 고양이 사진을 검색할 수 있다")
  void get_image_by_breeds_name() {
    String breedsName = catBreedsRepository.findAll().get(0).getName();
    List<CatImageResDto> catImageDetail = catImageService.getSearchListBy(breedsName);
    List<Breeds> searchedBreedList = catBreedsRepository.findAll().stream()
        .filter(catImage -> catImage.getName().equals(breedsName)).toList();

    assertThat(catImageDetail.size()).isEqualTo(searchedBreedList.size());

  }

  @Test
  @DisplayName("고양이 api데이터의 id로 고양이 사진 정보를 조회할 수 있다")
  void get_image_By_Display_id() {
    CatImage savedCatImage = catImageRepository.findAll().get(0);
    CatImageDetailResDto catImageDetail = catImageService.getByDisplayId(
        savedCatImage.getDisplayId());

    assertThat(catImageDetail.url()).isEqualTo(savedCatImage.getUrl());
  }


}