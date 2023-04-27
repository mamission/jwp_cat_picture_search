package com.search.cat_picture.dto;

public record CatPictureResponse(
	String id,
	String url,
	int width,
	int height,
	String name, // 품종
	String temperament, // 폼종
	String origin // 품종
) {
}
