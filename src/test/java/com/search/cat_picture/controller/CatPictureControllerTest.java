package com.search.cat_picture.controller;

import static org.mockito.BDDMockito.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;

import com.search.cat_picture.dto.CatPictureResponse;
import com.search.cat_picture.dto.CatPictureResponses;
import com.search.cat_picture.service.CatPictureService;

@AutoConfigureRestDocs
@WebMvcTest(CatPictureController.class)
@ExtendWith(RestDocumentationExtension.class)
class CatPictureControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CatPictureService service;

	private static final String BASE_REQUEST_URI = "/cats";

	@DisplayName("고양이 사진 랜덤 50장 조")
	@Test
	void getRandom50CatPictures() throws Exception {
		//given
		CatPictureResponses responses =
			new CatPictureResponses(List.of(
				new CatPictureResponses.SimpleCatPictureResponse("사진 id1", "고양이 사진1", "폼종명1"),
				new CatPictureResponses.SimpleCatPictureResponse("사진 id2", "고양이 사진2", "폼종명2"),
				new CatPictureResponses.SimpleCatPictureResponse("사진 id3", "고양이 사진3", "폼종명3")
			));

		given(service.getRandomPictures(50))
			.willReturn(responses);

		//when // then
		mockMvc.perform(get(BASE_REQUEST_URI + "/random50")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
			)
			.andExpect(status().isOk())
			.andDo(document("get-random-50-picture",
					preprocessRequest(prettyPrint()),
					preprocessResponse(prettyPrint()),
					responseFields(
						fieldWithPath("catPictures[]").type(JsonFieldType.ARRAY).description("사진 list"),
						fieldWithPath("catPictures[].id").type(JsonFieldType.STRING).description("id"),
						fieldWithPath("catPictures[].url").type(JsonFieldType.STRING).description("url"),
						fieldWithPath("catPictures[].name").type(JsonFieldType.STRING).description("폼종 이름")
					)
				)
			);
	}

	@DisplayName("품종으로 고양이 사진 조회")
	@Test
	void getCatPicturesByBreed() throws Exception {
		//given
		String name = "폼종 이름";
		CatPictureResponses responses =
			new CatPictureResponses(List.of(
				new CatPictureResponses.SimpleCatPictureResponse("사진 id1", "고양이 사진1", name),
				new CatPictureResponses.SimpleCatPictureResponse("사진 id2", "고양이 사진2", name)
			));

		given(service.getPicturesByBreedName(name))
			.willReturn(responses);

		//when // then
		mockMvc.perform(get(BASE_REQUEST_URI + "/search")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.param("q", name)
			)
			.andExpect(status().isOk())
			.andDo(document("get-picture-by-breed",
					preprocessRequest(prettyPrint()),
					preprocessResponse(prettyPrint()),
					queryParameters(
						parameterWithName("q").description("품종 이름")
					),
					responseFields(
						fieldWithPath("catPictures[]").type(JsonFieldType.ARRAY).description("사진 list"),
						fieldWithPath("catPictures[].id").type(JsonFieldType.STRING).description("id"),
						fieldWithPath("catPictures[].url").type(JsonFieldType.STRING).description("url"),
						fieldWithPath("catPictures[].name").type(JsonFieldType.STRING).description("폼종 이름")
					)
				)
			);
	}

	@DisplayName("사진id로 고양이 사진 조회")
	@Test
	void getCatPictureById() throws Exception {
		//given
		String id = "AAA";

		CatPictureResponse response =
			new CatPictureResponse(id, "이미지 url", 55, 55, "폼종 이름", "폼종 성질", "폼종 origin");

		given(service.getPictureById(id))
			.willReturn(response);

		//when // then
		mockMvc.perform(get(BASE_REQUEST_URI + "/{imageId}", id)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
			)
			.andExpect(status().isOk())
			.andDo(document("get-picture-by-id",
					preprocessRequest(prettyPrint()),
					preprocessResponse(prettyPrint()),
					pathParameters(
						parameterWithName("imageId").description("주문 id")
					),
					responseFields(
						fieldWithPath("id").type(JsonFieldType.STRING).description("id"),
						fieldWithPath("url").type(JsonFieldType.STRING).description("url"),
						fieldWithPath("width").type(JsonFieldType.NUMBER).description("가로 크기"),
						fieldWithPath("height").type(JsonFieldType.NUMBER).description("세로 크기"),
						fieldWithPath("name").type(JsonFieldType.STRING).description("폼종 이름"),
						fieldWithPath("temperament").type(JsonFieldType.STRING).description("폼종 성질"),
						fieldWithPath("origin").type(JsonFieldType.STRING).description("폼종 origin")
					)
				)
			);
	}
}