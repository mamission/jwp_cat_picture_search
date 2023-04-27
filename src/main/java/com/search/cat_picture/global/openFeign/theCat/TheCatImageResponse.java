package com.search.cat_picture.global.openFeign.theCat;

import java.util.List;

public record TheCatImageResponse(
	String id,
	String url,
	Integer width,
	Integer height,
	List<TheCatBreedResponse> breeds
) {
}
