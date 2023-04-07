package com.cat.picture_search.presentation;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.cat.picture_search.domain.repository.CatPictureRepository;
import com.cat.picture_search.domain.storage.data.CatPicture;

@ActiveProfiles("test")
@AutoConfigureRestDocs
@AutoConfigureMockMvc
@SpringBootTest
@Transactional
class CatPictureSearchControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private CatPictureRepository catPictureRepository;

	@Test
	@DisplayName("고양이 사진을 무작위로 50개 가져올 수 있다.")
	void test_get_random_50_pictures() throws Exception {
		mockMvc.perform(get("/v1/cats/random50"))
			.andExpect(status().isOk())
			.andDo(print())
			.andDo(document("get-random-50-pictures",
				preprocessRequest(prettyPrint()),
				preprocessResponse(prettyPrint()),
				responseFields(
					fieldWithPath("data[]").type(JsonFieldType.ARRAY).description("반환되는 고양이 사진 목록입니다."),
					fieldWithPath("data[].id").type(JsonFieldType.STRING).description("고양이 사진 데이터 id, 유니크한 값입니다."),
					fieldWithPath("data[].url").type(JsonFieldType.STRING).description("고양이 사진에 해당하는 url 입니다."),
					fieldWithPath("data[].name").type(JsonFieldType.STRING)
						.description("사진에 등장한 고양이의 이름입니다. 없으면 빈 문자열(\"\")로 출력됩니다.")
				)));
	}

	@Test
	@DisplayName("품종을 기준으로 사진을 검색할 수 있다.")
	void test_get_searched_pictures_by_keyword() throws Exception {
		String keyword = "United Kingdom";

		mockMvc.perform(get("/v1/cats/search?q=" + keyword))
			.andExpect(status().isOk())
			.andDo(print())
			.andDo(document("search-pictures-by-keyword",
				preprocessRequest(prettyPrint()),
				preprocessResponse(prettyPrint()),
				queryParameters(
					parameterWithName("q").description("검색하고자 하는 키워드")
				),
				responseFields(
					fieldWithPath("data[]").type(JsonFieldType.ARRAY).description("반환되는 고양이 사진 목록입니다."),
					fieldWithPath("data[].id").type(JsonFieldType.STRING).description("고양이 사진 데이터 id, 유니크한 값입니다."),
					fieldWithPath("data[].url").type(JsonFieldType.STRING).description("고양이 사진에 해당하는 url 입니다."),
					fieldWithPath("data[].name").type(JsonFieldType.STRING)
						.description("사진에 등장한 고양이의 이름입니다. 없으면 빈 문자열(\"\")로 출력됩니다.")
				)));
	}

	@Test
	@DisplayName("아이디를 이용하여 하나의 고양이 사진 정보를 가져올 수 있다.")
	void test_get_one_picture_by_id() throws Exception {
		CatPicture catPicture = catPictureRepository.findAll().get(0);

		mockMvc.perform(get("/v1/cats/{id}", catPicture.getId()))
			.andExpect(status().isOk())
			.andDo(print())
			.andDo(document("get-one-picture",
				preprocessRequest(prettyPrint()),
				preprocessResponse(prettyPrint()),
				pathParameters(
					parameterWithName("id").description("찾고자 하는 데이터의 id 값입니다.")
				),
				responseFields(
					fieldWithPath("data").type(JsonFieldType.OBJECT).description("반환되는 고양이 사진 객체입니다."),
					fieldWithPath("data.name").type(JsonFieldType.STRING)
						.description("사진에 등장한 고양이의 이름입니다. 없으면 빈 문자열(\"\")로 출력됩니다."),
					fieldWithPath("data.id").type(JsonFieldType.STRING).description("고양이 사진 데이터 id, 유니크한 값입니다."),
					fieldWithPath("data.url").type(JsonFieldType.STRING).description("고양이 사진에 해당하는 url 입니다."),
					fieldWithPath("data.width").type(JsonFieldType.NUMBER).description("고양이 사진의 너비 입니다."),
					fieldWithPath("data.height").type(JsonFieldType.NUMBER).description("고양이 사진의 높이 입니다."),
					fieldWithPath("data.temperament").type(JsonFieldType.STRING)
						.description("사진 속 고양이의 품종 정보 중 기질에 해당합니다. 없으면 빈 문자열(\"\")로 출력됩니다."),
					fieldWithPath("data.origin").type(JsonFieldType.STRING)
						.description("사진 속 고양이의 품종 정보 중 기원 지역입니다. 없으면 빈 문자열(\"\")로 출력됩니다.")
				)));
	}

}