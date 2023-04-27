package com.search.cat_picture.global.openFeign.theCat;

import java.util.List;

public record TheCatSimpleImageResponse(
	String id,
	String url,
	List<SimpleBreedResponse> breeds
) {
	public record SimpleBreedResponse(
		String name,
		String id
	) {
	}
}
