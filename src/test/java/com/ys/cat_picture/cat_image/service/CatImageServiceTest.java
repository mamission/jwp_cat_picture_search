package com.ys.cat_picture.cat_image.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;

import com.ys.cat_picture.cat_image.dto.CatImageResponse;
import com.ys.cat_picture.infra.client.CatApiClient;
import com.ys.cat_picture.infra.client.response.CatOneResponse;

import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.api.introspector.ConstructorPropertiesArbitraryIntrospector;

@SpringBootTest
class CatImageServiceTest {

	@MockBean
	private CatApiClient catApiClient;

	@Autowired
	private CatImageService catImageService;

	private final FixtureMonkey fixtureMonkey = FixtureMonkey.builder()
		.objectIntrospector(ConstructorPropertiesArbitraryIntrospector.INSTANCE)
		.defaultNotNull(true)
		.build();

	@DisplayName("랜덤이미지 50개를 반환한다.")
	@Transactional
	@Test
	void randomImages() {
		//given
		List<CatOneResponse> responses = fixtureMonkey.giveMeBuilder(CatOneResponse.class)
			.sampleList(50);

		given(catApiClient.getRandomImages(50, true))
			.willReturn(responses);

		//when
		List<CatImageResponse> randomImages = catImageService.getRandomImages();

		//then
		assertThat(randomImages).hasSize(50);
	}

}