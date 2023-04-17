package com.prgrms.thecatapi.common.loader;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.prgrms.thecatapi.cat.Photo;
import com.prgrms.thecatapi.common.external.dto.PhotoDto;
import com.prgrms.thecatapi.common.external.CatApiClient;
import com.prgrms.thecatapi.repository.PhotoRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Transactional
@RequiredArgsConstructor
public class DataUploader implements CommandLineRunner {
	private final int limit = 50;
	private final PhotoRepository photoRepository;
	private final CatApiClient catApiClient;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void run(String... args) throws Exception {
		if (photoRepository.count() > limit) {
			return;
		}

		log.info("외부에서 데이터를 받아옵니다.");
		insertData();
		log.info("데이터 저장 성공.");
	}

	private void insertData() {
		List<PhotoDto> data = getRandomData();

		for (PhotoDto photoDto : data) {
			Photo photo = new Photo(photoDto.id(), photoDto.url(), photoDto.width(), photoDto.height());

			entityManager.persist(photo);
		}

		entityManager.flush();
		entityManager.clear();
	}

	private List<PhotoDto> getRandomData() {
		return catApiClient.getRandomImage(limit);
	}
}
