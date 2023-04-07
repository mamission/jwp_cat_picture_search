package com.ys.cat_picture.cat_breed.repository;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ys.cat_picture.cat_breed.domain.CatBreed;
import com.ys.cat_picture.support.RepositoryTest;

@RepositoryTest
class CatBreedRepositoryTest {

	@Autowired
	private CatBreedRepository catBreedRepository;

	@DisplayName("existsData - 존재하지 않으면 false를 반환한다.")
	@Test
	void existsData_false() {
		assertThat(catBreedRepository.existsData()).isFalse();
	}

	@DisplayName("existsData - 존재하면 true를 반환한다.")
	@Test
	void existsData_true() {
		//given
		catBreedRepository.saveAll(breeds);

		//when & then
		assertThat(catBreedRepository.existsData()).isTrue();
	}

	private List<CatBreed> breeds =
		List.of(
			CatBreed.builder()
				.externalId("beng")
				.name("Bengal")
				.temperament("Alert, Agile, Energetic, Demanding, Intelligent")
				.origin("United States")
				.build(),
			CatBreed.builder()
				.externalId("cymr")
				.name("Cymric")
				.temperament("Gentle, Loyal, Intelligent, Playful")
				.origin("Canada")
				.build()
		);
}