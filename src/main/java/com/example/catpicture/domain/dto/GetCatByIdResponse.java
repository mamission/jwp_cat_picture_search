package com.example.catpicture.domain.dto;

import com.example.catpicture.domain.entity.CatPicture;

public record GetCatByIdResponse(
	Details data
) {
	public GetCatByIdResponse(CatPicture catPicture) {
		this(new Details(
			catPicture.getName(),
			catPicture.photoId(),
			catPicture.url(),
			catPicture.width(),
			catPicture.height(),
			catPicture.getTemperament(),
			catPicture.getOrigin()
		));
	}

	public record Details(
		String name,
		String id,
		String url,
		int width,
		int height,
		String temperament,
		String origin
	) {
	}
}
