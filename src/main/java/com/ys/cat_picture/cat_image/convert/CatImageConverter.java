package com.ys.cat_picture.cat_image.convert;

import java.util.List;

import com.ys.cat_picture.cat_breed.convert.CatBreedConverter;
import com.ys.cat_picture.cat_image.domain.CatImage;
import com.ys.cat_picture.infra.client.response.CatOneResponse;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CatImageConverter {

	public static List<CatImage> convert(List<CatOneResponse> responses) {
		return responses.stream()
			.map(CatImageConverter::convert)
			.toList();
	}

	public static CatImage convert(CatOneResponse catOneResponse) {
		CatOneResponse.BreedResponse breedResponse = null;

		if (!catOneResponse.breeds().isEmpty()) {
			breedResponse = catOneResponse.breeds().get(0);
		}

		return new CatImage(catOneResponse.id(), catOneResponse.url(), catOneResponse.width(), catOneResponse.height(),
			CatBreedConverter.convert(breedResponse).orElse(null)
		);
	}

}
