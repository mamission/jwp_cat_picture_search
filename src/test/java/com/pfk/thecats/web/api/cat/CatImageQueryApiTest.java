package com.pfk.thecats.web.api.cat;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import com.pfk.thecats.domain.cat.dao.CatBreedRepository;
import com.pfk.thecats.domain.cat.dao.CatRepository;
import com.pfk.thecats.domain.cat.domain.Cat;
import com.pfk.thecats.domain.cat.domain.CatBreed;
import com.pfk.thecats.domain.type.Image;
import com.pfk.thecats.infra.thecatapi.CatDataInitializer;

@AutoConfigureMockMvc
@AutoConfigureRestDocs
@SpringBootTest
@Transactional
class CatImageQueryApiTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CatDataInitializer catDataInitializer;

	@Autowired
	private CatRepository catRepository;

	@Autowired
	private CatBreedRepository catBreedRepository;

	@Test
	@DisplayName("랜덤한 50개의 고양이 사진 목록을 조회할 수 있다.")
	void getRandom50() throws Exception {
		// given
		CatBreed breed = new CatBreed("name", "temperament", "origin");
		this.catBreedRepository.save(breed);

		Image image = new Image("https://cdn2.thecatapi.com/images/sourceId", 400, 300);
		List<Cat> cats = List.of(
			new Cat("cat1", breed, image),
			new Cat("cat2", breed, image)
		);
		this.catRepository.saveAll(cats);

		// when & then
		ResultActions resultActions = this.mockMvc.perform(
				get("/cats/random50")
			)
			.andExpect(status().isOk())
			.andDo(print());

		// REST Docs
		resultActions.andDo(
			document("cat-image-get-random-50",
				responseFields(
					fieldWithPath("data[].name").description("Breed Name"),
					fieldWithPath("data[].id").description("Cat Id"),
					fieldWithPath("data[].url").description("Image URL")
				)
			)
		);
	}

	@Test
	@DisplayName("고양이 품종으로 고양이 이미지를 조회할 수 있다.")
	void getAllByBreedName() throws Exception {
		// given
		String breedName = "Abyssinian";
		CatBreed breed = new CatBreed(breedName, "temperament", "origin");
		this.catBreedRepository.save(breed);

		Image image = new Image("https://cdn2.thecatapi.com/images/sourceId", 400, 300);
		List<Cat> cats = List.of(
			new Cat("cat1", breed, image),
			new Cat("cat2", breed, image)
		);
		this.catRepository.saveAll(cats);

		// when & then
		ResultActions resultActions = this.mockMvc.perform(
				get("/cats/search").param("q", breedName)
			)
			.andExpect(status().isOk())
			.andDo(print());

		// REST Docs
		resultActions.andDo(
			document("cat-image-get-all-by-breed-name",
				requestParameters(
					parameterWithName("q").description("Breed Name")
				),
				responseFields(
					fieldWithPath("data[].name").description("Breed Name"),
					fieldWithPath("data[].id").description("Cat Id"),
					fieldWithPath("data[].url").description("Image URL")
				)
			)
		);
	}
}