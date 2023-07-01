package com.geonwoo.thecatapi.domain.catImage.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import com.geonwoo.thecatapi.domain.catImage.model.CatImage;

@DataJpaTest
@Sql(scripts = {"/sql/cat_dummy.sql"})
class CatImageRepositoryTest {

	@Autowired
	private CatImageRepository catImageRepository;

	@Test
	@DisplayName("[성공] 랜덤한 이미지를 최대 50개 조회한다.")
	void getRandom50Images() {
		List<CatImage> catList = catImageRepository.getRandom50Images();
		for (CatImage catImage : catList) {
			assertNotNull(catImage.getId());
			assertNotNull(catImage.getName());
			assertNotNull(catImage.getUrl());
		}
	}

	@Test
	@DisplayName("[성공] 고양이 품종으로 고양이를 조회한다.")
	void findAllByName() {
		List<CatImage> catList = catImageRepository.findAllByName("Nebelung");
		for (CatImage catImage : catList) {
			assertNotNull(catImage.getId());
			assertNotNull(catImage.getName());
			assertNotNull(catImage.getUrl());
		}
	}

}