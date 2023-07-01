package com.geonwoo.thecatapi.domain.catImage.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.geonwoo.thecatapi.domain.catImage.converter.CatImageConverter;
import com.geonwoo.thecatapi.domain.catImage.dto.response.ImageResponse;
import com.geonwoo.thecatapi.domain.catImage.exception.CatImageNotFoundException;
import com.geonwoo.thecatapi.domain.catImage.model.CatImage;
import com.geonwoo.thecatapi.domain.catImage.repository.CatImageRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CatImageService {

	private final CatImageRepository catImageRepository;

	public List<ImageResponse> getRandom50Images() {

		return catImageRepository.getRandom50Images()
			.stream()
			.map(CatImageConverter::toImageResponse)
			.toList();
	}

	public List<ImageResponse> getImagesByBreed(String breedName) {

		return catImageRepository.findAllByName(breedName)
			.stream()
			.map(CatImageConverter::toImageResponse)
			.toList();
	}

	public ImageResponse getImageById(String imageId) {

		CatImage catImage = catImageRepository.findById(imageId)
			.orElseThrow(CatImageNotFoundException::new);

		return CatImageConverter.toImageDetailResponse(catImage);
	}
}
