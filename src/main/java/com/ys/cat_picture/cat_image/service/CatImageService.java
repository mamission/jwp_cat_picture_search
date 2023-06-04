package com.ys.cat_picture.cat_image.service;

import static com.ys.cat_picture.cat_image.convert.CatImageConverter.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ys.cat_picture.cat_breed.convert.CatBreedConverter;
import com.ys.cat_picture.cat_breed.domain.CatBreed;
import com.ys.cat_picture.cat_breed.repository.CatBreedRepository;
import com.ys.cat_picture.cat_image.domain.CatImage;
import com.ys.cat_picture.cat_image.dto.CatImageDetailResponse;
import com.ys.cat_picture.cat_image.dto.CatImageResponse;
import com.ys.cat_picture.cat_image.repository.CatImageRepository;
import com.ys.cat_picture.infra.client.CatApiClient;
import com.ys.cat_picture.infra.client.response.CatOneResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CatImageService {

	private static final int MAX_RANDOM_IMAGE_COUNT = 50;
	private static final int DEFAULT_QUERY_COUNT = 10;

	private final CatApiClient catApiClient;
	private final CatImageRepository catImageRepository;
	private final CatBreedRepository catBreedRepository;

	@Transactional
	public List<CatImageResponse> getRandomImages() {
		List<CatOneResponse> responses = catApiClient.getRandomImages(MAX_RANDOM_IMAGE_COUNT, true);

		List<CatImage> catImages = convert(responses);

		return catImageRepository.saveAllIgnore(catImages)
			.stream().map(it -> {
				String name = null;
				if (it.getBreed() != null) {
					name = it.getBreed().getName();
				}
				return new CatImageResponse(it.getExternalId(), it.getUrl(), name);
			}).toList();
	}

	@Transactional
	public CatImageDetailResponse getById(String catId) {
		CatImage findCatImage = catImageRepository.findByExternalId(catId)
			.orElseGet(() ->
				{
					CatOneResponse catOneResponse = catApiClient.getImageById(catId);

					CatImage.CatImageBuilder catImageBuilder = CatImage.builder()
						.externalId(catOneResponse.id())
						.url(catOneResponse.url())
						.width(catOneResponse.width())
						.height(catOneResponse.height());

					Optional<CatBreed> catBreedOptional = CatBreedConverter.convert(catOneResponse);

					if (catBreedOptional.isPresent()) {
						CatBreed catBreed = catBreedRepository.findByExternalId(catBreedOptional.get().getExternalId())
							.orElseGet(() -> catBreedRepository.save(catBreedOptional.get()));
						catImageBuilder.breed(catBreed);
					}

					return catImageRepository.save(catImageBuilder.build());
				}
			);

		return convert(findCatImage);
	}

	@Transactional
	public List<CatImageResponse> searchByQuery(String breedName) {
		List<CatImage> catImages = catImageRepository.findAllByBreedNameWithBreed(breedName,
			PageRequest.of(0, DEFAULT_QUERY_COUNT));

		if (catImages.isEmpty()) {
			Optional<CatBreed> catBreedOptional = catBreedRepository.findByName(breedName);

			if (catBreedOptional.isEmpty()) {
				return Collections.emptyList();
			}

			CatBreed catBreed = catBreedOptional.get();

			List<CatOneResponse> catOneResponses = catApiClient.findAllByBreedId(catBreed.getExternalId(),
				DEFAULT_QUERY_COUNT);

			catImages.addAll(catImageRepository.saveAll(convert(catOneResponses, catBreed)));
		}

		return catImages.stream()
			.map(it -> new CatImageResponse(it.getExternalId(), it.getUrl(), it.getBreed().getName()))
			.collect(Collectors.toList());
	}

}
