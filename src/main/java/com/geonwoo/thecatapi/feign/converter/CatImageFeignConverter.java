package com.geonwoo.thecatapi.feign.converter;

import com.geonwoo.thecatapi.domain.catImage.model.CatImage;
import com.geonwoo.thecatapi.feign.response.ImageFeignResponse;

public class CatImageFeignConverter {

	public static CatImage toCatImage(ImageFeignResponse imageFeignResponse) {
		return CatImage.builder()
			.id(imageFeignResponse.id())
			.name(imageFeignResponse.breeds().get(0).name())
			.url(imageFeignResponse.url())
			.width(imageFeignResponse.width())
			.height(imageFeignResponse.height())
			.temperament(imageFeignResponse.breeds().get(0).temperament())
			.origin(imageFeignResponse.breeds().get(0).origin())
			.build();
	}
}
