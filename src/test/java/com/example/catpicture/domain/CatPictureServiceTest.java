package com.example.catpicture.domain;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.jdbc.Sql;

import com.example.catpicture.domain.dto.GetCatByIdResponse;
import com.example.catpicture.domain.dto.GetCatsByBreedResponse;
import com.example.catpicture.domain.dto.GetRandomCatsResponse;
import com.example.catpicture.domain.dto.RandomCatResponse;
import com.example.catpicture.domain.entity.CatPicture;

@DataJpaTest
@Sql(scripts = "/dummy.sql")
class CatPictureServiceTest {

	@Autowired
	private CatPictureRepository catPictureRepository;

	@Autowired
	private CatPictureService catPictureService;

	@DisplayName("고양이 사진은 랜덤으로 조회될 수 있다.")
	@Test
	void getRandom() {
		// given
		List<RandomCatResponse> expectData = catPictureRepository.findAll()
			.stream()
			.map(RandomCatResponse::new)
			.toList();
		GetRandomCatsResponse expect = new GetRandomCatsResponse(expectData);

		// when
		GetRandomCatsResponse actual = catPictureService.getRandom(3);

		// then
		Assertions.assertThat(actual.data().get(0))
			.isIn(expect.data());
		Assertions.assertThat(actual.data().get(1))
			.isIn(expect.data());
		Assertions.assertThat(actual.data().get(2))
			.isIn(expect.data());
	}

	@DisplayName("고양이 사진은 품종으로 조회될 수 있다.")
	@Test
	void getByBreed() {
		// given
		List<CatPicture> allPictures = catPictureRepository.findAll();
		CatPicture testData1 = allPictures.get(0);
		CatPicture testData2 = allPictures.get(3);

		GetCatsByBreedResponse expect = GetCatsByBreedResponse.from(List.of(testData1, testData2));

		// when
		GetCatsByBreedResponse actual = catPictureService.getByBreed(testData1.getName());

		// then
		Assertions.assertThat(actual)
			.isEqualTo(expect);
	}

	@DisplayName("고양이 사진은 아이디로 조회될 수 있다.")
	@Test
	void getByPhotoId() {
		// given
		List<CatPicture> allPictures = catPictureRepository.findAll();
		CatPicture testData = allPictures.get(1);

		GetCatByIdResponse expect = new GetCatByIdResponse(testData);

		// when
		GetCatByIdResponse actual = catPictureService.getByPhotoId(testData.photoId());

		// then
		Assertions.assertThat(actual)
			.isEqualTo(expect);
	}

	@TestConfiguration
	static class TestConfig {

		@Bean
		public CatPictureService catPictureService(CatPictureRepository catPictureRepository) {
			return new CatPictureService(catPictureRepository);
		}
	}
}