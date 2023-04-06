package com.cat.picture_search.domain.dto;

import java.util.stream.Collectors;

import com.cat.picture_search.domain.dto.api.BreedsDetail;
import com.cat.picture_search.domain.dto.api.CatPictureSimple;

public record CatPictureSimpleRes(
	String id,
	String url,
	String name
) {

	private static final String DELIMITER = ",";

	public static CatPictureSimpleRes of(CatPictureSimple catPictureSimple) {
		return new CatPictureSimpleRes(
			catPictureSimple.id(),
			catPictureSimple.url(),
			catPictureSimple.breeds()
				.stream()
				.map(BreedsDetail::name)
				.collect(Collectors.joining(DELIMITER))
		);
	}
}
