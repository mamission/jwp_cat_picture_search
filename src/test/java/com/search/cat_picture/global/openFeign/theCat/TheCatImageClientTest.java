package com.search.cat_picture.global.openFeign.theCat;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.search.cat_picture.global.openFeign.FeignTest;

@FeignTest
class TheCatImageClientTest {

	@Autowired
	private TheCatImageClient theCatImageClient;

	private final String IMAGE_ID = "zly78vxmW";

	private final String BREED_ID = "emau";

	@Test
	@DisplayName("The cat api - 랜덤 이미지 list 조회")
	void findImages() {
		var images = theCatImageClient.findRandomPictures(10);

		assertThat(images.size()).isEqualTo(10);
	}

	@Test
	@DisplayName("The cat api - id로 이미지 조회")
	void findBreedById() {
		var findImage = theCatImageClient.findCatPictureById(IMAGE_ID);

		assertThat(findImage.id()).isEqualTo(IMAGE_ID);
	}

	@Test
	@DisplayName("The cat api - 품종으로 이미지 조회")
	void findBreedByBreed() {
		var findImage = theCatImageClient.findPicturesByBreed(BREED_ID);

		assertThat(findImage.isEmpty()).isFalse();
	}
}