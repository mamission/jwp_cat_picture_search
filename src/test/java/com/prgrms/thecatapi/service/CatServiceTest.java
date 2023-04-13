package com.prgrms.thecatapi.service;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import com.prgrms.thecatapi.cat.Breed;
import com.prgrms.thecatapi.cat.Photo;
import com.prgrms.thecatapi.cat.PhotoBreed;
import com.prgrms.thecatapi.cat.Weight;
import com.prgrms.thecatapi.common.dto.DetailResponse;
import com.prgrms.thecatapi.common.dto.SimpleResponse;
import com.prgrms.thecatapi.common.dto.SimpleResponses;
import com.prgrms.thecatapi.repository.PhotoBreedRepository;

@Transactional
@SpringBootTest
class CatServiceTest {

	@Autowired
	private CatService catService;

	@Autowired
	private PhotoBreedRepository photoBreedRepository;

	private String photoId = "photoId";
	private String url = "https://";
	private String breedId = "breedId";
	private String breedName = "beng";

	private Photo photo;
	private Breed breed;

	@BeforeEach
	void setup() {
		photo = new Photo(photoId, url, 600, 600);
		breed = new Breed(breedId, new Weight("imperial", "metric"), breedName, "temperament", "origin");

		PhotoBreed photoBreed = new PhotoBreed(photo, breed);
		photoBreedRepository.save(photoBreed);
	}

	@Test
	@DisplayName("findRandom50Photo - 50개의 랜덤 이미지를 가져온다.")
	void get50RandomImage() {
		//given
		int offset = 0;
		int size = 50;
		PageRequest pageRequest = PageRequest.of(0, size);

		//when
		SimpleResponses random50Photo = catService.findRandom50Photo(pageRequest);
		List<SimpleResponse> data = random50Photo.data();

		//then
		Assertions.assertEquals(50, data.size());
	}

	@Test
	@DisplayName("findById - photoId로 특정 이미지를 조회할 수 있다.")
	void findById() {
		//given & when
		DetailResponse result = catService.findById(photoId);

		//then
		assertThat(result)
			.hasFieldOrPropertyWithValue("id", photo.getId())
			.hasFieldOrPropertyWithValue("url", photo.getUrl())
			.hasFieldOrPropertyWithValue("width", photo.getWidth())
			.hasFieldOrPropertyWithValue("height", photo.getHeight())
			.extracting("breeds").asList().hasSize(1);
	}

	@Test
	@DisplayName("findByKeyword - 품종 이름으로 특정 이미지를 조회할 수 있다.")
	void findByKeyword() {
		//given
		int offset = 0;
		int size = 1;

		PageRequest pageRequest = PageRequest.of(offset, size);

		//when
		SimpleResponses result = catService.findByBreedName(breedName, pageRequest);
		List<SimpleResponse> data = result.data();

		//then
		assertThat(data).hasSize(size);

		assertThat(data.get(0))
			.extracting("id", "url", "name")
			.containsExactly(photoId, url, breedName);
	}
}