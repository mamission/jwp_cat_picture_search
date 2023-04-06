package com.geonwoo.thecatapi.domain.catImage.controller;

import static org.springframework.http.MediaType.*;
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
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
class CatImageControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	@DisplayName("[성공] 랜덤한 50개의 고양이 사진을 반환한다.")
	void getRandom50Images() throws Exception {

		mockMvc.perform(get("/cats/random50"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(APPLICATION_JSON))
			.andExpect(jsonPath("$.data").exists())
			.andDo(print())
			.andDo(document("get-random50Images",
				responseFields(
					fieldWithPath("data.[].id").description("이미지 아이디"),
					fieldWithPath("data.[].url").description("이미지 url"),
					fieldWithPath("data.[].name").description("고양이 품종 이름")
				)));

	}

	@Test
	@DisplayName("[성공] 파라미터로 넘긴 고양이 품종과 일치하는 고양이 사진을 반환한다.")
	void getImagesByBreed() throws Exception {

		String breedName = "Munchkin";

		mockMvc.perform(get("/cats/search/{q}", breedName))
			.andExpect(status().isOk())
			.andExpect(content().contentType(APPLICATION_JSON))
			.andExpect(jsonPath("$.data").exists())
			.andDo(print())
			.andDo(document("get-imagesByBreed",
				pathParameters(
					parameterWithName("q").description("품종 이름")
				),
				responseFields(
					fieldWithPath("data.[].id").description("이미지 아이디"),
					fieldWithPath("data.[].url").description("이미지 url"),
					fieldWithPath("data.[].name").description("고양이 품종 이름")
				)
			));
	}

	@Sql(scripts = {"/sql/cat_dummy.sql"})
	@Test
	@DisplayName("[성공] 아이디로 고양이 사진을 검색하여 반환한다.")
	void getImageById() throws Exception {

		mockMvc.perform(get("/cats")
				.param("id", "_6x-3TiCA"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(APPLICATION_JSON))
			.andExpect(jsonPath("$.data").exists())
			.andDo(print())
			.andDo(document("get-imageById",
				queryParameters(
					parameterWithName("id").description("이미지 아이디")
				),
				responseFields(
					fieldWithPath("data.id").description("이미지 아이디"),
					fieldWithPath("data.url").description("이미지 url"),
					fieldWithPath("data.name").description("고양이 품종 이름"),
					fieldWithPath("data.width").description("사진 넓이"),
					fieldWithPath("data.height").description("사진 높이"),
					fieldWithPath("data.temperament").description("고양이 성격"),
					fieldWithPath("data.origin").description("고양이 출신지")
				)));
	}
}