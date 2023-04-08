package com.pfk.thecats.infra.thecatapi;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.pfk.thecats.domain.cat.dao.CatBreedRepository;
import com.pfk.thecats.domain.cat.dao.CatRepository;
import com.pfk.thecats.domain.cat.domain.Cat;
import com.pfk.thecats.domain.cat.domain.CatBreed;
import com.pfk.thecats.domain.type.Image;
import com.pfk.thecats.infra.thecatapi.dto.CatImageSearchResponse;

@Component
public class CatDataInitializer implements CommandLineRunner {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	private final CatRepository catRepository;
	private final CatBreedRepository catBreedRepository;
	private final CatApiClient catApiClient;

	public CatDataInitializer(
		CatRepository catRepository,
		CatBreedRepository catBreedRepository,
		CatApiClient catApiClient
	) {
		this.catRepository = catRepository;
		this.catBreedRepository = catBreedRepository;
		this.catApiClient = catApiClient;
	}

	@Override
	public void run(String... args) throws Exception {
		if (!catRepository.anyCatExists()) {
			log.info("No Cat Data Exists. Start Generating Cat Data...");

			List<CatImageSearchResponse> responses = catApiClient.searchCatImages();
			responses.forEach(this::saveCat);
		}
	}

	@Transactional
	public void saveCat(CatImageSearchResponse catInfo) {
		CatBreed catBreed = saveCatBreedIfNotExists(catInfo.getBreed());
		Image image = new Image(catInfo.url(), catInfo.height(), catInfo.width());

		catRepository.save(new Cat(catInfo.id(), catBreed, image));
	}

	private CatBreed saveCatBreedIfNotExists(CatImageSearchResponse.Breed breedInfo) {
		Optional<CatBreed> possibleCatBreed = catBreedRepository.findCatBreedByName(breedInfo.name());

		if (possibleCatBreed.isPresent()) {
			return possibleCatBreed.get();
		}

		CatBreed catBreed = new CatBreed(breedInfo.name(), breedInfo.temperament(), breedInfo.origin());
		return catBreedRepository.save(catBreed);
	}
}
