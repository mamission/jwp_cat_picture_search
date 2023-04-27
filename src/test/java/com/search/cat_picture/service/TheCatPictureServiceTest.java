package com.search.cat_picture.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import com.search.cat_picture.dto.CatPictureResponse;
import com.search.cat_picture.dto.CatPictureResponses;
import com.search.cat_picture.global.exception.BadRequestException;
import com.search.cat_picture.repository.CatPictureRepository;

@ActiveProfiles("test")
@SpringBootTest
@Transactional
class TheCatPictureServiceTest {

	@Autowired
	private CatPictureService catPictureService;

	@Autowired
	private CatPictureRepository catPictureRepository;

	@Test
	@DisplayName("랜덤 고양이 사진 조회")
	void getRandomPictures() {
		CatPictureResponses responses = catPictureService.getRandomPictures(10);

		assertThat(responses.catPictures().size()).isEqualTo(10);
	}

	@Test
	@DisplayName("품종별 고양이 사진 조회")
	void getPicturesByBreedName() {
		CatPictureResponses responses = catPictureService.getPicturesByBreedName("Abyssinian");

		assertThat(responses.catPictures().size()).isEqualTo(1);
	}

	@Test
	@DisplayName("존재하지 않는 품종 고양이 사진 조회")
	void getPicturesByBreedName_notExist() {
		CatPictureResponses responses = catPictureService.getPicturesByBreedName("영조");

		assertThat(responses.catPictures().size()).isEqualTo(0);
	}

	@Test
	@DisplayName("사진 id로 고양이 사진 조회_성공")
	void getPictureById_success() {
		catPictureRepository.deleteById("ajs");
		assertThat(catPictureRepository.findById("ajs").isEmpty()).isTrue();
		CatPictureResponse response = catPictureService.getPictureById("ajs");

		assertThat(response.id()).isEqualTo("ajs");
		assertThat(catPictureRepository.findById("ajs").isPresent()).isTrue();
	}

	@Test
	@DisplayName("사진 id로 고양이 사진 조회_실패")
	void getPictureById_theCatApi_fail() {
		assertThat(catPictureRepository.findById("영지").isEmpty()).isTrue();
		assertThrows(BadRequestException.class, () -> {
			catPictureService.getPictureById("영지");
		});
	}

}