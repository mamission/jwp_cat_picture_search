package com.prgrms.thecatapi.repository;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.*;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.prgrms.thecatapi.common.dto.BreedResponse;

@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
class PhotoBreedRepositoryTest {

	@Autowired
	private PhotoBreedRepository photoBreedRepository;

	@Test
	@DisplayName("findBreedByPhotoId 메소드의 쿼리가 성공적으로 생성되는지 확인합니다.")
	void queryTest() {
		List<BreedResponse> result = photoBreedRepository.findBreedByPhotoId("Kf-zJDHCx");

		Assertions.assertEquals(1, result.size());
	}

}