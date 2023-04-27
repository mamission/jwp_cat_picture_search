package com.search.cat_picture.service;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import com.search.cat_picture.entity.CatBreed;
import com.search.cat_picture.repository.CatBreedRepository;

@ActiveProfiles("test")
@SpringBootTest
@Transactional
class TheCatBreedServiceTest {

	@Autowired
	private TheCatBreedService theCatBreedService;

	@Autowired
	private CatBreedRepository catBreedRepository;

	@DisplayName("품종 list를 the cat api에서 찾아와 저장")
	@Test
	void saveAllBreeds() {
		catBreedRepository.deleteAll();
		theCatBreedService.saveAllBreeds(10);

		List<CatBreed> allBreeds = catBreedRepository.findAll();
		assertThat(allBreeds.size()).isEqualTo(10);
	}

	@DisplayName("품정 이름으로 db에 저장된 품종의 id를 찾는다_성공")
	@Test
	void getIdByName_success() {
		theCatBreedService.saveAllBreeds(10);
		Optional<String> id = theCatBreedService.findIdByName("Abyssinian");
		assertThat(id.isPresent()).isTrue();
		assertThat(id.get()).isEqualTo("abys");
	}

	@DisplayName("품정 이름으로 db에 저장된 품종의 id를 찾는다_실패")
	@Test
	void getIdByName_fail() {
		Optional<String> id = theCatBreedService.findIdByName("영지");
		assertThat(id).isEmpty();
	}

}