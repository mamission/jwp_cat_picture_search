package com.pfk.thecats.domain.cat.dto;

import java.util.List;

import com.pfk.thecats.domain.cat.domain.Cat;

public record CatImageQueryResponse(
	String id,
	String url,
	String name
) {

	public static CatImageQueryResponse from(Cat cat) {
		return new CatImageQueryResponse(cat.sourceId(), cat.getImageUrl(), cat.getBreedName());
	}

	public static List<CatImageQueryResponse> from(List<Cat> cats) {
		return cats.stream()
			.map(CatImageQueryResponse::from)
			.toList();
	}
}
