package com.example.catpicturesearch.application;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.catpicturesearch.application.clients.TheCatClient;
import com.example.catpicturesearch.application.domain.Breed;
import com.example.catpicturesearch.application.domain.CatBreed;
import com.example.catpicturesearch.application.domain.CatImage;
import com.example.catpicturesearch.application.clients.dto.CatImagesRequest;
import com.example.catpicturesearch.application.repository.BreedRepository;
import com.example.catpicturesearch.application.repository.CatBreedRepository;
import com.example.catpicturesearch.application.repository.CatImageRepository;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class DataCollector implements CommandLineRunner {

	private final TheCatClient theCatClient;

	private final BreedRepository breedRepository;

	private final CatImageRepository catImageRepository;

	private final CatBreedRepository catBreedRepository;

	@Override
	@Transactional
	public void run(String... args) throws Exception {
		// Step 0. Check if there are no database records
		if (catBreedRepository.existsAny()) {
			// Terminate if database records exist
			log.info("Data Collector has already ended running because data has been collected.");
			return;
		}

		// Step 1. Collecting cat breeds
		List<Breed> breeds = getBreeds();
		breedRepository.bulkInsert(breeds);
		Map<String, Breed> breedMap = breeds.stream().collect(
				Collectors.toMap(
						Breed::getId,
						breed -> breed,
						(oldId, newId) -> oldId,
						ConcurrentHashMap::new
				));

		// Step 2. Collecting cat images
		for (Breed breed : breeds) {
			// Step 2-1. Search by breed
			CatImagesRequest imagesRequest = new CatImagesRequest(100, breed.getId(), true, true);

			// Step 2-2. Save cat images
			try {
				List<CatImage> catImages = getCatImages(imagesRequest);
				catImageRepository.bulkInsert(catImages);

				// Step 2-3. Save mapping entities
				Breed catBreed = breedMap.get(breed.getId());
				List<CatBreed> catBreeds = catImages.stream()
						.map(catImage -> CatBreed.builder()
								.catImage(catImage)
								.breed(catBreed)
								.build())
						.toList();
				catBreedRepository.bulkInsert(catBreeds);
			} catch (FeignException.TooManyRequests e) {
				// API Server Error (429 Too Many Requests) -> Ignore
				log.warn("Too Many Requests. The data collection for the following Bread was skipped : {}", imagesRequest.getBreed_ids());
			}
		}

		log.info("Successfully completed data collection.");
	}

	private List<Breed> getBreeds() {
		return theCatClient.getBreeds().stream()
				.map(breed -> Breed.builder()
						.id(breed.id())
						.name(breed.name())
						.temperament(breed.temperament())
						.origin(breed.origin())
						.build()
				).toList();
	}

	private List<CatImage> getCatImages(CatImagesRequest imagesRequest) {
		return theCatClient.getCatImages(imagesRequest).stream()
				.map(image -> CatImage.builder()
						.id(image.id())
						.width(image.width())
						.height(image.height())
						.url(image.url())
						.build())
				.toList();
	}

}
