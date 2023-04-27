package com.search.cat_picture.global.openFeign.theCat;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.search.cat_picture.global.openFeign.FeignTest;

@FeignTest
class TheCatBreedClientTest {

	@Autowired
	private TheCatBreedClient theCatBreedClient;

	private final String BREED_ID = "emau";

	@Test
	@DisplayName("The cat api - 품종 list 조회")
	void findBreeds() {
		var breeds = theCatBreedClient.findBreeds(10);

		assertThat(breeds.size()).isEqualTo(10);
	}

	@Test
	@DisplayName("The cat api - id로 품종 조회")
	void findBreedById() {
		var findBreed = theCatBreedClient.findBreedById(BREED_ID);

		assertThat(findBreed.id()).isEqualTo(BREED_ID);
	}
}