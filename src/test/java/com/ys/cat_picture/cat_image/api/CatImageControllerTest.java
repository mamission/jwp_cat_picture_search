package com.ys.cat_picture.cat_image.api;

import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Cat;
import com.github.javafaker.Faker;
import com.ys.cat_picture.cat_breed.domain.CatBreed;
import com.ys.cat_picture.cat_breed.repository.CatBreedRepository;
import com.ys.cat_picture.cat_image.domain.CatImage;
import com.ys.cat_picture.cat_image.repository.CatImageRepository;
import com.ys.cat_picture.infra.client.CatApiClient;
import com.ys.cat_picture.infra.client.response.CatOneResponse;
import com.ys.cat_picture.infra.client.response.CatOneResponse.BreedResponse;
import com.ys.cat_picture.support.RestDocsSupport;

@Sql(scripts = {"/sql/clean_up.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(properties = {"spring.config.location=classpath:application-test.yml"})
@AutoConfigureMockMvc
@ExtendWith(RestDocumentationExtension.class)
@Import(RestDocsSupport.class)
@SpringBootTest(properties = {"cat_api.url=https://api.thecatapi.com"})
class CatImageControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private CatApiClient catApiClient;

	@Autowired
	private RestDocumentationResultHandler restDocs;

	@Autowired
	private CatBreedRepository catBreedRepository;

	@Autowired
	private CatImageRepository catImageRepository;

	@BeforeEach
	void setUp(WebApplicationContext context, RestDocumentationContextProvider provider) {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
			.apply(MockMvcRestDocumentation.documentationConfiguration(provider))
			.alwaysDo(MockMvcResultHandlers.print())
			.alwaysDo(restDocs)
			.addFilters(new CharacterEncodingFilter("UTF-8", true))
			.build();
	}

	@DisplayName("랜덤한 이미지 50개를 반환한다.")
	@Test
	void random50Images() throws Exception {
		//given
		List<CatOneResponse> catOneResponses = dummy(50);

		given(catApiClient.getRandomImages(50, true))
			.willReturn(catOneResponses);

		//when
		ResultActions resultActions = mockMvc.perform(get("/cats/random50"));

		//then
		resultActions.andExpect(status().isOk())
			.andExpect(jsonPath("$.data[*].id").exists())
			.andExpect(jsonPath("$.data[*].url").exists())
			.andExpect(jsonPath("$.data[*].name").exists())
			.andExpect(jsonPath("$.data[*].length()", hasSize(50)));

		resultActions.andDo(restDocs.document(responseFields(
				fieldWithPath("data[].id").description("고양이 이미지 Id"),
				fieldWithPath("data[].url").description("고양이 이미지 url"),
				fieldWithPath("data[].name").description("고양이 종류")
			)
		));

		verify(catApiClient).getRandomImages(50, true);
	}

	@DisplayName("Id 값으로 고양이 이미지를 가져온다")
	@Test
	void getById() throws Exception {
		//given
		String imagePrefix = "https://cdn2.thecatapi.com/images/";
		String imageSuffix = ".jpg";

		Faker faker = new Faker();
		Cat cat = faker.cat();
		String breed = cat.breed();
		BreedResponse breedResponse = new BreedResponse(breed.substring(0, 3),
			breed, "abcd", "Super South Korean");
		String catId = cat.name() + faker.random().nextInt(0, 100);

		CatOneResponse catOneResponse = new CatOneResponse(catId, faker.random().nextInt(0, 500),
			faker.random().nextInt(0, 500),
			imagePrefix + catId + imageSuffix, List.of(breedResponse));

		given(catApiClient.getImageById(catId))
			.willReturn(catOneResponse);

		//when
		ResultActions resultActions = mockMvc.perform(get("/cats/{catId}", catId));

		// then
		resultActions.andExpect(status().isOk())
			.andExpect(jsonPath("$.data.id").exists())
			.andExpect(jsonPath("$.data.url").exists())
			.andExpect(jsonPath("$.data.name").exists())
		;

		resultActions.andDo(restDocs.document(

			pathParameters(
				parameterWithName("catId").description("고양이 사진 id 값")
			),

			responseFields(
				fieldWithPath("data.name").description("고양이 종류"),
				fieldWithPath("data.url").description("고양이 이미지 url"),
				fieldWithPath("data.id").description("고양이 이미지 id"),
				fieldWithPath("data.width").description("고양이 가로 길이"),
				fieldWithPath("data.height").description("고양이 세로 id"),
				fieldWithPath("data.temperament").description("고양이 기질"),
				fieldWithPath("data.origin").description("고양이 고향")
			)
		));

		verify(catApiClient).getImageById(catId);
	}

	@DisplayName("고양이 종류로 고양이를 찾는다.")
	@Test
	void searchByQuery() throws Exception {
		//given
		Faker faker = new Faker();
		Cat cat = faker.cat();
		String breed = cat.breed();
		List<CatImage> catImages = catImageDummy(breed);
		catImageRepository.saveAll(catImages);

		//when
		ResultActions resultActions = mockMvc.perform(get("/cats/search")
			.param("q", breed)
		);

		//then
		resultActions.andExpect(status().isOk())
			.andExpect(jsonPath("$.data[*].id").exists())
			.andExpect(jsonPath("$.data[*].url").exists())
			.andExpect(jsonPath("$.data[*].name").exists());

		resultActions.andDo(restDocs.document(

			queryParameters(
				parameterWithName("q").description("고양이의 품종(영어/한글)")
			)
			,
			responseFields(
				fieldWithPath("data[].id").description("고양이 이미지 Id"),
				fieldWithPath("data[].url").description("고양이 이미지 url"),
				fieldWithPath("data[].name").description("고양이 종류")
			)
		));

	}

	private List<CatImage> catImageDummy(String breed) {
		Faker faker = new Faker();
		Cat cat = faker.cat();
		CatBreed catBreed = new CatBreed(breed.substring(0, 3), breed, "temperament", "Super South Korea");

		catBreedRepository.save(catBreed);

		String imagePrefix = "https://cdn2.thecatapi.com/images/";
		String imageSuffix = ".jpg";

		return IntStream.range(0, 10)
			.mapToObj(it -> {
				String catId = cat.name() + faker.random().nextInt(0, 100);
				return new CatImage(catId, imagePrefix + catId + imageSuffix, faker.random().nextInt(0, 500),
					faker.random().nextInt(0, 500), catBreed);
			}).collect(Collectors.toList());
	}

	private List<CatOneResponse> dummy(int size) {
		String imagePrefix = "https://cdn2.thecatapi.com/images/";
		String imageSuffix = ".jpg";

		Faker faker = new Faker();
		Cat cat = faker.cat();

		return IntStream.range(0, size)
			.mapToObj(it -> {
				String breed = cat.breed();
				BreedResponse breedResponse = new BreedResponse(breed.substring(0, 3),
					breed, "abcd", "Super South Korea");
				String catId = cat.name() + faker.random().nextInt(0, 100);

				return new CatOneResponse(catId, faker.random().nextInt(0, 500), faker.random().nextInt(0, 500),
					imagePrefix + catId + imageSuffix, List.of(breedResponse));
			}).collect(Collectors.toList());
	}
}