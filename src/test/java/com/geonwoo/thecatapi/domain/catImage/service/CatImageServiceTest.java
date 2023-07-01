package com.geonwoo.thecatapi.domain.catImage.service;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import com.geonwoo.thecatapi.domain.catImage.dto.response.ImageResponse;
import com.geonwoo.thecatapi.domain.catImage.exception.CatImageNotFoundException;

@SpringBootTest
@Sql(scripts = {"/sql/cat_dummy.sql"})
@Transactional
class CatImageServiceTest {

	@Autowired
	private CatImageService catImageService;

	@Test
	@DisplayName("[성공] 50개의 랜덤한 이미지를 가져온다.")
	void getRandom50Images_success() {

		// when
		List<ImageResponse> images = catImageService.getRandom50Images();

		// then
		for (ImageResponse image : images) {
			assertThat(image).isNotNull();
			assertThat(image.id()).isNotEmpty();
			assertThat(image.url()).isNotEmpty();
			assertThat(image.name()).isNotEmpty();
		}
	}

	@Test
	@DisplayName("[성공] 해당 종의 이미지를 가져온다.")
	void getImagesByBreed_success() {

		// when
		List<ImageResponse> images = catImageService.getImagesByBreed("Russian Blue");

		// then
		for (ImageResponse image : images) {
			assertThat(image).isNotNull();
			assertThat(image.id()).isNotEmpty();
			assertThat(image.url()).isNotEmpty();
			assertThat(image.name()).isNotEmpty();
		}
	}

	@Test
	@DisplayName("[성공] 해당 id의 이미지를 가져온다.")
	void getImageById_success() {

		// when
		ImageResponse image = catImageService.getImageById("2bPsrIcp-");

		// then
		assertThat(image).isNotNull();
		assertThat(image.id()).isNotEmpty();
		assertThat(image.url()).isNotEmpty();
		assertThat(image.name()).isNotEmpty();
		assertThat(image.width()).isNotNull();
		assertThat(image.height()).isNotNull();
		assertThat(image.temperament()).isNotEmpty();
		assertThat(image.origin()).isNotEmpty();
	}

	@Test
	@DisplayName("[실패] 해당 id로 존재하지 않는 이미지를 가져오려고 할 때 CatNotFoundException이 발생한다")
	void getImageById_notFound() {
		// given
		String imageId = "invalidId";

		// when, then
		assertThatThrownBy(() -> catImageService.getImageById(imageId))
			.isInstanceOf(CatImageNotFoundException.class);
	}
}