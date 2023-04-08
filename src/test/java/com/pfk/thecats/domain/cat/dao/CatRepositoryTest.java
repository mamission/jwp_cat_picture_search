package com.pfk.thecats.domain.cat.dao;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.pfk.thecats.domain.cat.domain.Cat;
import com.pfk.thecats.domain.cat.domain.CatBreed;
import com.pfk.thecats.domain.type.Image;

@DataJpaTest
class CatRepositoryTest {

	@Autowired
	private CatRepository catRepository;

	@Autowired
	private CatBreedRepository catBreedRepository;

	@Test
	@DisplayName("Cat 데이터가 있으면, 데이터 존재 여부 확인 시 참이다.")
	void existsCat_returns_true_when_cat_exists() {
		// given
		CatBreed breed = new CatBreed("name", "temperament", "origin");
		catBreedRepository.save(breed);

		Image image = new Image("url", 400, 300);
		Cat cat = new Cat("sourceId", breed, image);
		catRepository.save(cat);

		// when
		boolean isCatExists = catRepository.anyCatExists();

		// then
		assertThat(isCatExists).isTrue();
	}

	@Test
	@DisplayName("Cat 데이터가 없으면, 데이터 존재 여부 확인 시 거짓이다.")
	void existsCat_returns_false_when_cat_does_not_exist() {
		// when
		boolean isCatExists = catRepository.anyCatExists();

		// then
		assertThat(isCatExists).isFalse();
	}

	@Test
	@DisplayName("랜덤한 Cat 데이터를 조회할 수 있다.")
	void findCatsOrderByRandom() {
		// given
		CatBreed breed = new CatBreed("name", "temperament", "origin");
		catBreedRepository.save(breed);

		int maxCatDataSize = 100;
		List<Cat> cats = newCats(maxCatDataSize, breed);
		catRepository.saveAll(cats);

		int sizeToFind = 50;

		// when
		List<Cat> findCats = catRepository.findCatsOrderByRandom(sizeToFind);

		// then
		int actualFoundSize = findCats.size();
		assertThat(actualFoundSize).isEqualTo(sizeToFind);
	}

	private List<Cat> newCats(int size, CatBreed breed) {
		List<Cat> cats = new ArrayList<>();

		for (int i = 1; i <= size; i++) {
			Image image = new Image("url" + i, i, i);
			Cat cat = new Cat("source" + i, breed, image);

			cats.add(cat);
		}

		return cats;
	}
}