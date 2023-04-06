package com.pfk.thecats.infra.thecatapi;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.pfk.thecats.domain.cat.application.CatReader;
import com.pfk.thecats.domain.cat.dao.CatRepository;
import com.pfk.thecats.domain.cat.entity.Cat;
import com.pfk.thecats.infra.thecatapi.dto.CatImageSearchResponse;

@Component
public class CatDataGenerator implements CommandLineRunner {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	private final CatRepository catRepository;
	private final CatReader catReader;
	private final CatApiClient catApiClient;

	public CatDataGenerator(
		CatRepository catRepository,
		CatReader catReader, CatApiClient catApiClient
	) {
		this.catRepository = catRepository;
		this.catReader = catReader;
		this.catApiClient = catApiClient;
	}

	@Override
	public void run(String... args) throws Exception {
		if (!catReader.anyCatExists()) {
			log.info("No Cat Data Exists. Start Generating Cat Data...");

			List<CatImageSearchResponse> responses = catApiClient.searchCatImages();

			List<Cat> cats = CatImageSearchResponse.mapToEntity(responses);
			catRepository.saveAll(cats);
		}
	}
}
