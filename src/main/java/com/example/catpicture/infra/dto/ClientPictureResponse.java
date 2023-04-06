package com.example.catpicture.infra.dto;

import java.util.List;

import com.example.catpicture.domain.entity.CatPicture;

public record ClientPictureResponse(
	String id,
	String url,
	int width,
	int height
) {
	public static List<CatPicture> toEntity(List<ClientPictureResponse> pictureResponses,
		ClientBreedResponse breedResponse) {
		return pictureResponses.stream()
			.map(response -> new CatPicture(
				response.id(),
				response.url(),
				response.width(),
				response.height(),
				breedResponse.toEntity()
			)).toList();
	}
}
