package com.example.catpicture.domain.dto;

import com.example.catpicture.domain.entity.CatPicture;

public record RandomCatResponse(
	String id,
	String url,
	String name
) {

	public RandomCatResponse(CatPicture catPicture) {
		this(catPicture.id(), catPicture.url(), catPicture.getName());
	}
}
