package com.pfk.thecats.web.api.cat;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
class CatQueryApiTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CatDataInitializer catDataInitializer;

	@Autowired
	private CatRepository catRepository;

	@Autowired
	private CatBreedRepository catBreedRepository;

	@Test
	@DisplayName("고양이 ID로 고양이 정보를 단건 조회할 수 있다.")
	void getOneBySourceId() throws Exception {
		// given
		CatBreed breed = new CatBreed("name", "temperament", "origin");
		catBreedRepository.save(breed);

		Image image = new Image("https://cdn2.thecatapi.com/images/sourceId", 400, 300);
		String sourceId = "sourceId";
		Cat cat = new Cat(sourceId, breed, image);
		catRepository.save(cat);

		// when & then
		ResultActions resultActions = mockMvc.perform(
				get("/cats/{sourceId}", sourceId)
			)
			.andExpect(status().isOk())
			.andDo(print());

		// REST Docs
		resultActions.andDo(
			document("cat-get-one-by-source-id",
				pathParameters(
					parameterWithName("sourceId").description("Cat Id")
				),
				responseFields(
					fieldWithPath("name").description("Breed Name"),
					fieldWithPath("id").description("Cat Id"),
					fieldWithPath("url").description("Image URL"),
					fieldWithPath("width").description("Image Width"),
					fieldWithPath("height").description("Image Height"),
					fieldWithPath("temperament").description("Cat Temperament"),
					fieldWithPath("origin").description("Breed Origin")
				)
			)
		);
	}
}