package com.cat.picture_search.domain.service;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Objects;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import com.cat.picture_search.domain.dto.CatPictureDetailRes;
import com.cat.picture_search.domain.dto.CatPictureSimpleRes;
import com.cat.picture_search.domain.storage.data.CatPicture;
import com.cat.picture_search.domain.storage.repository.CatPictureRepository;

@ActiveProfiles("test")
@SpringBootTest
@Transactional
class CatPictureSearchServiceTest {

	@Autowired
	private CatPictureSearchService catPictureSearchService;

	@Autowired
	private CatPictureRepository catPictureRepository;

	@Test
	@DisplayName("50개의 랜덤 사진을 가져올 수 있다.")
	void test_get_random_50_pictures() {
		List<CatPictureSimpleRes> catPictureSimpleRes = catPictureSearchService.getRandomPic50();

		int expectedSize = 50;

		assertThat(catPictureSimpleRes).hasSize(expectedSize);
	}

	@Test
	@DisplayName("United States가 기원 지역인 고양이들을 검색할 수 있다.")
	void test_get_searched_picture_by_keyword() {
		String keyword = "United States";

		List<CatPictureSimpleRes> catPictureSimpleRes = catPictureSearchService.search(keyword);

		assertThat(catPictureSimpleRes).allMatch(catPictureDto -> {
			CatPicture catPicture = catPictureRepository.findById(catPictureDto.id()).get();
			return Objects.equals(catPicture.getBreeds().getOrigin(), keyword);
		});
	}

	@Test
	@DisplayName("id를 이용하여 특정 고양이 사진 정보를 조회할 수 있다.")
	void test_get_one_detail_cat_picture() {
		CatPicture catPicture = catPictureRepository.findAll().get(0);

		CatPictureDetailRes findCatPicture = catPictureSearchService.getOne(catPicture.getId());

		assertThat(findCatPicture).usingRecursiveComparison()
			.isEqualTo(
				CatPictureDetailRes.of(catPicture)
			);
	}

}