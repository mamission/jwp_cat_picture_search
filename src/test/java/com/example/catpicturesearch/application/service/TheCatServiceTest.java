package com.example.catpicturesearch.application.service;

import static com.example.catpicturesearch.application.provider.TheCatProvider.getBENG;
import static com.example.catpicturesearch.application.provider.TheCatProvider.getCatImagesByBreedId;
import static com.example.catpicturesearch.application.provider.TheCatProvider.getCatImagesByCatImageId;
import static com.example.catpicturesearch.application.provider.TheCatProvider.getEBUR;
import static com.example.catpicturesearch.application.provider.TheCatProvider.getRandom50;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.catpicturesearch.application.domain.Breed;
import com.example.catpicturesearch.application.domain.CatBreed;
import com.example.catpicturesearch.application.dto.CatImageDetailResponse;
import com.example.catpicturesearch.application.dto.CatImageResponse;
import com.example.catpicturesearch.application.repository.CatBreedRepository;

@ExtendWith(MockitoExtension.class)
class TheCatServiceTest {

	@Mock
	private CatBreedRepository catBreedRepository;

	@InjectMocks
	private TheCatService theCatService;

	@Test
	@DisplayName("랜덤한 50개의 이미지 목록을 불러온다.")
	void testGetRandom50() {
		// Given
		given(catBreedRepository.findAllOrderByRandom(anyInt()))
				.willReturn(getRandom50());

		// When
		List<CatImageResponse> catImageResponses = theCatService.getRandom50();

		// Then
		Assertions.assertThat(catImageResponses).isNotEmpty();
	}

	@Test
	@DisplayName("품종 식별자 기준으로 이미지 목록을 불러온다.")
	void testGetCatImagesByBreedId() {
		// Given
		Breed breed = getBENG();
		given(catBreedRepository.findAllByBreedId(breed.getId()))
				.willReturn(getCatImagesByBreedId(breed.getId()));

		// When
		List<CatImageResponse> catImageResponses = theCatService.getCatImagesByBreedId(breed.getId());

		// Then
		Assertions.assertThat(catImageResponses).isNotEmpty();
		Assertions.assertThat(catImageResponses)
				.extracting(CatImageResponse::name)
				.contains(breed.getName());
	}

	@Test
	@DisplayName("이미지 식별자 기준으로 상세 정보를 불러온다.")
	void testGetCatImagesByCatImageId() {
		// Given
		CatBreed breed = getCatImagesByBreedId(getEBUR().getId()).get(0);
		given(catBreedRepository.findAllByCatImageId(breed.getCatImage().getId()))
				.willReturn(getCatImagesByCatImageId(breed.getCatImage().getId()));

		// When
		CatImageDetailResponse catImageDetailResponse = theCatService.getCatImagesByCatImageId(breed.getCatImage().getId());

		// Then
		Assertions.assertThat(catImageDetailResponse).isNotNull();
		Assertions.assertThat(catImageDetailResponse.id()).isEqualTo(breed.getCatImage().getId());
		Assertions.assertThat(catImageDetailResponse.name()).isEqualTo(breed.getBreed().getName());
		Assertions.assertThat(catImageDetailResponse.width()).isEqualTo(breed.getCatImage().getWidth());
		Assertions.assertThat(catImageDetailResponse.height()).isEqualTo(breed.getCatImage().getHeight());
		Assertions.assertThat(catImageDetailResponse.temperament()).isEqualTo(breed.getBreed().getTemperament());
		Assertions.assertThat(catImageDetailResponse.origin()).isEqualTo(breed.getBreed().getOrigin());
	}

}