package com.pfk.thecats.domain.cat.dto;

import com.pfk.thecats.domain.cat.domain.Cat;
import com.pfk.thecats.domain.cat.domain.CatBreed;
import com.pfk.thecats.domain.type.Image;

public record CatQueryResponse(
	String name,
	String id,
	String url,
	int width,
	int height,
	String temperament,
	String origin
) {

	public static CatQueryResponse from(Cat cat) {
		CatBreed breed = cat.breed();
		Image image = cat.image();

		return new CatQueryResponse(
			breed.name(),
			cat.sourceId(),
			image.url(),
			image.width(),
			image.height(),
			breed.temperament(),
			breed.origin()
		);
	}
}
