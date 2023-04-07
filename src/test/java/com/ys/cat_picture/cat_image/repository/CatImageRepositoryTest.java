package com.ys.cat_picture.cat_image.repository;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import com.ys.cat_picture.cat_breed.domain.CatBreed;
import com.ys.cat_picture.cat_image.domain.CatImage;
import com.ys.cat_picture.support.RepositoryTest;
@Sql(scripts = {"/sql/breed_sample.sql", "/sql/cat_image_sample.sql"})
@RepositoryTest
class CatImageRepositoryTest {

	@Autowired
	private CatImageRepository catImageRepository;

	@DisplayName("이미 존재하는 데이터는 저장하지 않는다.")
	@Test
	void saveAllIgnore_existsDataNotInsert() {
	    //given
		List<CatImage> catImages = List.of(
			new CatImage("36v", "https://cdn2.thecatapi.com/images/36v.jpg", 540, 720,
				existsBreeds.get(0)),
			new CatImage("24u", "https://cdn2.thecatapi.com/images/24u.png", 564, 400,
				existsBreeds.get(1)));
		int exitingCatImageDataSize = catImageRepository.findAll().size();

		//when
		catImageRepository.saveAllIgnore(catImages);

		//then
		assertThat(catImageRepository.findAll()).hasSize(exitingCatImageDataSize);
	}

	@DisplayName("Breed 가 이미 존재하면 외래키를 맺는다.")
	@Test
	void saveAllIgnoreTest() {
		//given
		List<CatImage> catImages = List.of(
			new CatImage("aa", "https://cdn2.thecatapi.com/images/36v.jpg", 540, 720,
				existsBreeds.get(0)),
			new CatImage("bb", "https://cdn2.thecatapi.com/images/24u.png", 564, 400,
				existsBreeds.get(1)));

		//when
		List<CatImage> saveCatImages = catImageRepository.saveAllIgnore(catImages);

		//then
		assertThat(saveCatImages)
			.extracting(it -> it.getBreed().getId())
			.isNotNull();
	}

	private List<CatBreed> existsBreeds =
		List.of(
			CatBreed.builder()
				.externalId("bamb")
				.name("Bengal")
				.temperament("Alert, Agile, Energetic, Demanding, Intelligent")
				.origin("United States")
				.build(),
			CatBreed.builder()
				.externalId("bali")
				.name("Cymric")
				.temperament("Gentle, Loyal, Intelligent, Playful")
				.origin("Canada")
				.build()
		);

}