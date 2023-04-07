package com.example.catpicturesearch.application.mapper;

import java.util.List;

import com.example.catpicturesearch.application.domain.CatBreed;
import com.example.catpicturesearch.application.dto.CatImageDetailResponse;
import com.example.catpicturesearch.application.dto.CatImageResponse;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TheCatMapper {

	public static CatImageResponse toCatImageResponse(CatBreed catBreed) {
		return new CatImageResponse(
				catBreed.getCatImage().getId(),
				catBreed.getCatImage().getUrl(),
				catBreed.getBreed().getName()
		);
	}

	public static List<CatImageResponse> toCatImageResponses(List<CatBreed> catBreeds) {
		return catBreeds.stream()
				.map(TheCatMapper::toCatImageResponse)
				.toList();
	}

	public static CatImageDetailResponse toCatImageDetailResponse(CatBreed catBreed) {
		return new CatImageDetailResponse(
				catBreed.getCatImage().getId(),
				catBreed.getCatImage().getUrl(),
				catBreed.getBreed().getName(),
				catBreed.getCatImage().getWidth(),
				catBreed.getCatImage().getHeight(),
				catBreed.getBreed().getTemperament(),
				catBreed.getBreed().getOrigin()
		);
	}

}
