package com.ys.cat_picture.cat_image.convert;

import java.util.List;
import java.util.Objects;

import com.ys.cat_picture.cat_breed.convert.CatBreedConverter;
import com.ys.cat_picture.cat_breed.domain.CatBreed;
import com.ys.cat_picture.cat_image.domain.CatImage;
import com.ys.cat_picture.cat_image.dto.CatImageDetailResponse;
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

	public static List<CatImage> convert(List<CatOneResponse> responses, CatBreed catBreed) {
		return responses.stream()
			.map(it -> convert(it, catBreed))
			.toList();
	}

	public static CatImage convert(CatOneResponse catOneResponse, CatBreed catBreed) {

		return new CatImage(catOneResponse.id(),
			catOneResponse.url(),
			catOneResponse.width(),
			catOneResponse.height(),
			catBreed
		);
	}


	public static CatImage convert(CatOneResponse catOneResponse) {
		CatOneResponse.BreedResponse breedResponse = null;

		if (catOneResponse.breeds() != null && !catOneResponse.breeds().isEmpty()) {
			breedResponse = catOneResponse.breeds().get(0);
		}

		return new CatImage(catOneResponse.id(),
			catOneResponse.url(),
			catOneResponse.width(),
			catOneResponse.height(),
			CatBreedConverter.convert(breedResponse).orElse(null)
		);
	}

	public static CatImageDetailResponse convert(CatImage catImage) {
		CatBreed breed = catImage.getBreed();
		if (Objects.nonNull(catImage.getBreed())) {
			return new CatImageDetailResponse(breed.getName(), catImage.getExternalId(),
				catImage.getUrl(), catImage.getWidth().toString(), catImage.getHeight().toString(),
				breed.getTemperament(), breed.getOrigin());
		}

		return new CatImageDetailResponse(null, catImage.getExternalId(),
			catImage.getUrl(), catImage.getWidth().toString(), catImage.getHeight().toString(),
			null, null);
	}

}
