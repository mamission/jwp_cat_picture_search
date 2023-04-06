package com.geonwoo.thecatapi.domain.catImage.converter;

import com.geonwoo.thecatapi.domain.catImage.dto.response.ImageResponse;
import com.geonwoo.thecatapi.domain.catImage.model.CatImage;

public class CatImageConverter {

	public static ImageResponse toImageResponse(CatImage catImage) {
		return ImageResponse.create(
			catImage.getId(),
			catImage.getName(),
			catImage.getUrl()
		);
	}

	public static ImageResponse toImageDetailResponse(CatImage catImage) {
		return ImageResponse.builder()
			.id(catImage.getId())
			.url(catImage.getUrl())
			.name(catImage.getName())
			.width(catImage.getWidth())
			.height(catImage.getHeight())
			.temperament(catImage.getTemperament())
			.origin(catImage.getOrigin())
			.build();
	}

}
