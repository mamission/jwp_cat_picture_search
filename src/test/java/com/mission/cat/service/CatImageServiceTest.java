package com.mission.cat.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.cat.mission.catInfo.dto.CatInfoDetailResDto;
import com.cat.mission.catInfo.dto.CatInfoSimpleResDto;
import com.cat.mission.catInfo.entity.CatInfo;
import com.cat.mission.catInfo.repository.CatInfoRepository;
import com.cat.mission.catInfo.service.CatInfoService;
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
  private CatInfoService catImageService;

  @Autowired
  private CatInfoRepository catInfoRepository;

  @Test
  @DisplayName("랜덤으로 고양이 사진 50개를 가져올 수 있다")
  void get_50_random_images() {
    List<CatInfoSimpleResDto> randomImages = catImageService.getRandomImages();

    assertThat(randomImages.size()).isEqualTo(50);
  }

  @Test
  @DisplayName("고양이 품종이름으로 고양이 사진을 검색할 수 있다")
  void get_image_by_breeds_name() {
    String breedsName = catInfoRepository.findAll().get(0).getBreeds().getName();
    List<CatInfoSimpleResDto> catImageDetail = catImageService.getSearchListBy(breedsName);
    List<CatInfo> searchedBreedList = catInfoRepository.findAll().stream()
        .filter(catImage -> catImage.getBreeds().getName().equals(breedsName)).toList();

    assertThat(catImageDetail.size()).isEqualTo(searchedBreedList.size());

  }

  @Test
  @DisplayName("고양이 api데이터의 id로 고양이 사진 정보를 조회할 수 있다")
  void get_image_By_Display_id() {
    CatInfo savedCatImage = catInfoRepository.findAll().get(0);
    CatInfoDetailResDto catImageDetail = catImageService.getByDisplayId(
        savedCatImage.getDisplayId());

    assertThat(catImageDetail.url()).isEqualTo(savedCatImage.getImage().getUrl());
  }


}