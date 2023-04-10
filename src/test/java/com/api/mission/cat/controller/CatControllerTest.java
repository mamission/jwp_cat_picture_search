package com.api.mission.cat.controller;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.request.RequestDocumentation.queryParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.api.mission.cat.entity.Cat;
import com.api.mission.cat.repository.CatRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@AutoConfigureMockMvc
@AutoConfigureRestDocs
@SpringBootTest
@Transactional
class CatControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private CatRepository catRepository;

  @Test
  @DisplayName("랜덤으로 고양이사진을 50개 가져올 수 있다")
  void get_50_random_image() throws Exception {
    mockMvc.perform(get("/cats/random50"))
        .andExpect(status().isOk())
        .andDo(print())
        .andDo(document("get-50-random-images",
            preprocessRequest(prettyPrint()),
            preprocessResponse(prettyPrint()),
            responseFields(
                fieldWithPath("data.[].id").description("고양이 api 이미지 데이터 아이디"),
                fieldWithPath("data.[].url").description("고양이 이미지 url"),
                fieldWithPath("data.[].name").description("고양이 품종 이름")
            )));
  }

  @Test
  @DisplayName("고양이의 품종이름으로 고양이 사진을 검색할 수 있다")
  void get_cat_image_by_breeds_name() throws Exception {

    String breedsName = catRepository.findAll()
        .get(0)
        .getBreeds()
        .getName();

    mockMvc.perform(get("/cats/search?q=" + breedsName))
        .andExpect(status().isOk())
        .andDo(print())
        .andDo(document("search-by-keyword",
            preprocessRequest(prettyPrint()),
            preprocessResponse(prettyPrint()),
            queryParameters(
                parameterWithName("q").description("검색하고자 하는 품종 명")
            ),
            responseFields(
                fieldWithPath("data.[].id").description("고양이 api 이미지 데이터 아이디"),
                fieldWithPath("data.[].url").description("고양이 이미지 url"),
                fieldWithPath("data.[].name").description("고양이 품종 이름")
            )));
  }


  @Test
  @DisplayName("아이디를 이용하여 하나의 고양이 사진 정보를 가져올 수 있다.")
  void test_get_one_picture_by_id() throws Exception {
    Cat catImage = catRepository.findAll()
        .get(0);

    mockMvc.perform(get("/cats/{id}", catImage.getDisplayId()))
        .andExpect(status().isOk())
        .andDo(print())
        .andDo(document("get-by-id",
            preprocessRequest(prettyPrint()),
            preprocessResponse(prettyPrint()),
            pathParameters(
                parameterWithName("id").description("고양이 api 이미지 데이터 아이디")
            ),
            responseFields(
                fieldWithPath("data.id").description("고양이 api 이미지 데이터 아이디"),
                fieldWithPath("data.url").description("고양이 이미지 url"),
                fieldWithPath("data.name").description("고양이 품종 이름"),
                fieldWithPath("data.width").description("사진 넓이"),
                fieldWithPath("data.height").description("사진 높이"),
                fieldWithPath("data.temperament").description("고양이 기질"),
                fieldWithPath("data.origin").description("고양이 출신지")
            )));
  }

}