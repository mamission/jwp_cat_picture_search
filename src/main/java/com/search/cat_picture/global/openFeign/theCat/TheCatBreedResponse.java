package com.search.cat_picture.global.openFeign.theCat;

public record TheCatBreedResponse(
	String id,
	String name,
	String temperament,
	String origin
) {
}
