package com.ys.cat_picture.cat_image.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ys.cat_picture.cat_image.domain.CatImage;
import com.ys.cat_picture.cat_image.convert.CatImageConverter;
import com.ys.cat_picture.cat_image.dto.CatImageResponse;
import com.ys.cat_picture.cat_image.repository.CatImageRepository;
import com.ys.cat_picture.infra.client.CatApiClient;
import com.ys.cat_picture.infra.client.response.CatOneResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CatImageService {

	private final CatApiClient catApiClient;

	private final CatImageRepository catImageRepository;

	private static final int MAX_RANDOM_IMAGE_COUNT = 50;

	@Transactional
	public List<CatImageResponse> getRandomImages() {

		List<CatOneResponse> responses = catApiClient.getRandomImages(MAX_RANDOM_IMAGE_COUNT, true);

		List<CatImage> catImages = CatImageConverter.convert(responses);

		return catImageRepository.saveAllIgnore(catImages)
			.stream().map(it -> {
				String name = null;
				if (it.getBreed() != null) {
					name = it.getBreed().getName();
				}
				return new CatImageResponse(it.getExternalId(), it.getUrl(), name);
			}).toList();
	}

}
