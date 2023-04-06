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
		return responses.stream().map(it -> {
			CatOneResponse.BreedResponse breedResponse = null;

			if (!it.breeds().isEmpty()) {
				breedResponse = it.breeds().get(0);
			}

			return new CatImage(it.id(), it.url(), it.width(), it.height(),
				CatBreedConverter.convert(breedResponse));
		}).toList();
	}

}
