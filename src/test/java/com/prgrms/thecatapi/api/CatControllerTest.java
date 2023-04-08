package com.prgrms.thecatapi.api;

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
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
class CatControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Test
	@DisplayName("getRandom50Images - 랜덤 고양이 사진을 반환한다.")
	void getRandom50Images() throws Exception {

		mockMvc.perform(get("/cats/random50"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(APPLICATION_JSON))
			.andExpect(jsonPath("$.data").exists())
			.andDo(print())
			.andDo(document("randomImages",
				responseFields(
					fieldWithPath("data.[].id").description("이미지 ID"),
					fieldWithPath("data.[].url").description("이미지 url"),
					fieldWithPath("data.[].name").description("고양이 품종 이름")
				)));
	}

	@Test
	@DisplayName("[성공] 파라미터로 넘긴 고양이 품종과 일치하는 고양이 사진을 반환한다.")
	void getImagesByBreed() throws Exception {

		String breedName = "beng";

		mockMvc.perform(get("/cats/search")
				.param("q", breedName))
			.andExpect(status().isOk())
			.andExpect(content().contentType(APPLICATION_JSON))
			.andExpect(jsonPath("$.data").exists())
			.andDo(print())
			.andDo(document("findBy-breedName",
				requestParameters(
					parameterWithName("q").description("품종 이름")
				),
				responseFields(
					fieldWithPath("data.[].id").description("이미지 ID"),
					fieldWithPath("data.[].url").description("이미지 url"),
					fieldWithPath("data.[].name").description("고양이 품종 이름")
				)
			));
	}

	@Test
	@DisplayName("[성공] 아이디로 고양이 사진을 검색하여 반환한다.")
	void getImageById() throws Exception {

		String photoId = "Kf-zJDHCx";

		mockMvc.perform(get("/cats/{id}", photoId))
			.andExpect(status().isOk())
			.andExpect(content().contentType(APPLICATION_JSON))
			.andDo(print())
			.andDo(document("findBy-photoId",
				pathParameters(
					parameterWithName("id").description("이미지 아이디")
				),
				responseFields(
					fieldWithPath("id").description("이미지 ID"),
					fieldWithPath("url").description("이미지 url"),
					fieldWithPath("width").description("이미지 width"),
					fieldWithPath("height").description("이미지 height"),
					fieldWithPath("breeds.[].id").description("고양이 품종 ID"),
					fieldWithPath("breeds.[].name").description("고양이 품종 이름"),
					fieldWithPath("breeds.[].temperament").description("고양이 성격"),
					fieldWithPath("breeds.[].origin").description("고양이 출신지")
				)));
	}
}