package com.prgrms.thecatapi.repository;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
class PhotoRepositoryTest {
	@Autowired
	private PhotoRepository photoRepository;

	@Test
	@DisplayName("해당 메소드의 쿼리가 성공적으로 생성되는지 확인합니다.")
	void queryTest() {
		PageRequest pageRequest = PageRequest.of(0, 10);
		photoRepository.findRandom50Photos(pageRequest);
	}

	@Test
	@DisplayName("해당 메소드의 쿼리가 성공적으로 생성되는지 확인합니다.")
	void queryTest2() {
		PageRequest pageRequest = PageRequest.of(0, 10);
		photoRepository.findByKeyword("beng", pageRequest);
	}
}