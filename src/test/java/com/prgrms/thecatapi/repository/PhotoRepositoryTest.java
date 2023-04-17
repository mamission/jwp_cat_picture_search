package com.prgrms.thecatapi.repository;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.*;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;

import com.prgrms.thecatapi.common.dto.SimpleResponse;

@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
class PhotoRepositoryTest {

	@Autowired
	private PhotoRepository photoRepository;

	@Test
	@DisplayName("findRandom50Photos 메소드의 쿼리가 성공적으로 생성되는지 확인합니다.")
	void queryTest() {
		int size = 50;
		PageRequest pageRequest = PageRequest.of(0, size);
		List<SimpleResponse> result = photoRepository.findRandom50Photos(pageRequest);

		assertEquals(size, result.size());
	}

	@Test
	@DisplayName("findByKeyword 메소드의 쿼리가 성공적으로 생성되는지 확인합니다.")
	void queryTest2() {
		PageRequest pageRequest = PageRequest.of(0, 10);
		List<SimpleResponse> result = photoRepository.findByKeyword("Siamese", pageRequest);

		assertFalse(result.isEmpty());
	}
}