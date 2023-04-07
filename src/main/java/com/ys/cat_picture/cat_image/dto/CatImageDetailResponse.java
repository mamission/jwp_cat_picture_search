package com.ys.cat_picture.cat_image.dto;

public record CatImageDetailResponse(
	String name,
	String id,
	String url,
	String width,
	String height,
	String temperament,
	String origin
) {
}
