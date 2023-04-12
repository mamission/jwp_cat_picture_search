package com.api.mission.cat.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.api.mission.cat.dto.CatDetailResDto;
import com.api.mission.cat.dto.CatSimpleResDto;
import com.api.mission.cat.entity.Cat;
import com.api.mission.cat.repository.CatRepository;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class CatServiceTest {

  @Autowired
  private CatService catService;

  @Autowired
  private CatRepository catRepository;

  @Test
  @DisplayName("랜덤으로 고양이 사진 50개를 가져올 수 있다")
  void get_50_random_images() {
    List<CatSimpleResDto> randomImages = catService.getRandomImages();

    assertThat(randomImages.size()).isEqualTo(50);
  }

  @Test
  @DisplayName("고양이 품종이름으로 고양이 사진을 검색할 수 있다")
  void get_image_by_breeds_name() {
    String breedsName = catRepository.findAll().get(0).getBreeds().getName();
    List<CatSimpleResDto> catImageDetail = catService.getSearchListBy(breedsName);
    List<Cat> searchedBreedList = catRepository.findAll().stream()
        .filter(catImage -> catImage.getBreeds().getName().equals(breedsName)).toList();

    assertThat(catImageDetail.size()).isEqualTo(searchedBreedList.size());

  }

  @Test
  @DisplayName("고양이 api데이터의 id로 고양이 사진 정보를 조회할 수 있다")
  void get_image_By_Display_id() {
    Cat savedCatImage = catRepository.findAll().get(0);
    CatDetailResDto catImageDetail = catService.getByDisplayId(
        savedCatImage.getDisplayId());

    assertThat(catImageDetail.url()).isEqualTo(savedCatImage.getImage().getUrl());
  }


}