package com.example.catpicturesearch.application.api;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.request.RequestDocumentation.queryParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
class TheCatControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void testGetRandom50() throws Exception {
		mockMvc.perform(get("/cats/random50"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data").isNotEmpty())
				.andDo(print())
				.andDo(document("getRandom50",
						responseFields(
								fieldWithPath("data").type(JsonFieldType.ARRAY).description("랜덤한 50개의 고양이 사진 목록입니다."),
								fieldWithPath("data.[].id").type(JsonFieldType.STRING).description("고양이 사진 ID"),
								fieldWithPath("data.[].name").type(JsonFieldType.STRING).description("고양이 품종명"),
								fieldWithPath("data.[].url").type(JsonFieldType.STRING).description("고양이 사진 URL")
						))
				);
	}

	@Test
	void testGetCatImagesByBreedId() throws Exception {
		String breedId = "beng";

		mockMvc.perform(get("/cats/search")
						.param("q", breedId))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data").isNotEmpty())
				.andDo(print())
				.andDo(document("getCatImagesByBreedId",
						queryParameters(
								parameterWithName("q").description("고양이의 품종(영어/한글)")
						),
						responseFields(
								fieldWithPath("data").type(JsonFieldType.ARRAY).description("Keyword로 검색된 고양이 사진 목록입니다."),
								fieldWithPath("data.[].id").type(JsonFieldType.STRING).description("고양이 사진 ID"),
								fieldWithPath("data.[].name").type(JsonFieldType.STRING).description("고양이 품종명"),
								fieldWithPath("data.[].url").type(JsonFieldType.STRING).description("고양이 사진 URL")
						))
				);
	}

	@Test
	void testGetCatImagesByCatImageId() throws Exception {
		String catImageId = "ATYs2BetM";

		mockMvc.perform(RestDocumentationRequestBuilders.get("/cats/{id}", catImageId))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data").isNotEmpty())
				.andDo(print())
				.andDo(document("getCatImagesByCatImageId",
						pathParameters(
								parameterWithName("id").description("고양이 사진의 id값 입니다.")
						),
						responseFields(
								fieldWithPath("data").type(JsonFieldType.OBJECT).description("Id로 검색된 고양이 사진 입니다."),
								fieldWithPath("data.id").type(JsonFieldType.STRING).description("고양이 사진 ID"),
								fieldWithPath("data.name").type(JsonFieldType.STRING).description("고양이 품종명"),
								fieldWithPath("data.url").type(JsonFieldType.STRING).description("고양이 사진 URL"),
								fieldWithPath("data.width").type(JsonFieldType.NUMBER).description("사진 가로 크기"),
								fieldWithPath("data.height").type(JsonFieldType.NUMBER).description("사진 세로 크기"),
								fieldWithPath("data.temperament").type(JsonFieldType.STRING).description("고양이 특징"),
								fieldWithPath("data.origin").type(JsonFieldType.STRING).description("고양이 혈통")
						))
				);
	}

	@Test
	void testError() throws Exception {
		mockMvc.perform(get("/cats/search"))
				.andExpect(status().isInternalServerError())
				.andExpect(jsonPath("$.code").isNotEmpty())
				.andExpect(jsonPath("$.message").isNotEmpty())
				.andDo(print())
				.andDo(document("error",
						responseFields(
								fieldWithPath("code").type(JsonFieldType.STRING).description("에러 코드"),
								fieldWithPath("message").type(JsonFieldType.STRING).description("에러 상세 내용")
						))
				);
	}

}