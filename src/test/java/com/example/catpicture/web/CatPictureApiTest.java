package com.example.catpicture.web;

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
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.catpicture.domain.CatPictureRepository;
import com.example.catpicture.domain.entity.CatPicture;

@AutoConfigureRestDocs
@AutoConfigureMockMvc
@SpringBootTest
class CatPictureApiTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private CatPictureRepository catPictureRepository;

	@DisplayName("고양이 사진은 랜덤으로 50개 조회될 수 있다.")
	@Test
	void getRandomCats() throws Exception {
		mockMvc.perform(get("/cats/random50"))
			.andExpect(status().isOk())
			.andDo(print())
			.andDo(document("get-50-random-pictures",
				responseFields(
					fieldWithPath("data[]").type(JsonFieldType.ARRAY).description("고양이 사진 목록"),
					fieldWithPath("data[].id").type(JsonFieldType.STRING).description("고양이 사진 id"),
					fieldWithPath("data[].url").type(JsonFieldType.STRING).description("고양이 사진 url"),
					fieldWithPath("data[].name").type(JsonFieldType.STRING).description("고양이 품종 이름")
				)));
	}

	@DisplayName("고양이 사진은 품종으로 조회될 수 있다.")
	@Test
	void getCatsByBreed() throws Exception {
		CatPicture catPicture = catPictureRepository.findAll().get(0);

		mockMvc.perform(get("/cats/search?q=" + catPicture.getName()))
			.andExpect(status().isOk())
			.andDo(print())
			.andDo(document("get-pictures-by-name",
				queryParameters(
					parameterWithName("q").description("품종 이름")
				),
				responseFields(
					fieldWithPath("data[]").type(JsonFieldType.ARRAY).description("고양이 사진 목록"),
					fieldWithPath("data[].id").type(JsonFieldType.STRING).description("고양이 사진 id"),
					fieldWithPath("data[].url").type(JsonFieldType.STRING).description("고양이 사진 url"),
					fieldWithPath("data[].name").type(JsonFieldType.STRING).description("고양이 품종 이름")
				)));
	}

	@DisplayName("고양이 사진은 id 로 조회될 수 있다.")
	@Test
	void getCatsByPhotoId() throws Exception {
		CatPicture catPicture = catPictureRepository.findAll().get(0);

		mockMvc.perform(get("/cats/{id}", catPicture.photoId()))
			.andExpect(status().isOk())
			.andDo(print())
			.andDo(document("get-picture-by-id",
				pathParameters(
					parameterWithName("id").description("고양이 사진 id")
				),
				responseFields(
					fieldWithPath("data").type(JsonFieldType.OBJECT).description("고양이 사진"),
					fieldWithPath("data.name").type(JsonFieldType.STRING).description("고양이 품종 이름"),
					fieldWithPath("data.id").type(JsonFieldType.STRING).description("고양이 사진 id"),
					fieldWithPath("data.url").type(JsonFieldType.STRING).description("고양이 사진 url"),
					fieldWithPath("data.width").type(JsonFieldType.NUMBER).description("고양이 사진 가로 크기"),
					fieldWithPath("data.height").type(JsonFieldType.NUMBER).description("고양이 사진 세로 크기"),
					fieldWithPath("data.temperament").type(JsonFieldType.STRING).description("고양이 품종 기질"),
					fieldWithPath("data.origin").type(JsonFieldType.STRING).description("고양이 품종 기원지")
				)));
	}
}