package com.example.catpicture.domain.dto;

import java.util.List;

import com.example.catpicture.domain.entity.CatPicture;

public record GetCatsByBreedResponse(
	List<GetCatByBreedResponse> catsByBreed
) {
	public static GetCatsByBreedResponse from(List<CatPicture> catPictures) {
		return new GetCatsByBreedResponse(
			catPictures.stream()
				.map(GetCatByBreedResponse::new)
				.toList()
		);
	}

	public record GetCatByBreedResponse(
		String id,
		String url,
		String name
	) {
		public GetCatByBreedResponse(CatPicture catPicture) {
			this(catPicture.photoId(), catPicture.url(), catPicture.getName());
		}
	}
}
