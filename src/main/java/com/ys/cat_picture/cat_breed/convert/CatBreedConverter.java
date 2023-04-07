package com.ys.cat_picture.cat_breed.convert;

import java.util.Optional;

import com.ys.cat_picture.cat_breed.domain.CatBreed;
import com.ys.cat_picture.infra.client.response.CatOneResponse;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CatBreedConverter {

	public static Optional<CatBreed> convert(CatOneResponse.BreedResponse response) {
		if (response == null) {
			return Optional.empty();
		}

		return Optional.of(CatBreed.builder()
			.externalId(response.id())
			.name(response.name())
			.temperament(response.temperament())
			.origin(response.origin())
			.build());
	}
}
