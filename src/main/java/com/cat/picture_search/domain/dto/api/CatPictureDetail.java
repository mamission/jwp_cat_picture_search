package com.cat.picture_search.domain.dto.api;

import java.util.List;

public record CatPictureDetail(
	String id,
	String url,
	long width,
	long height,
	List<BreedsDetail> breeds
) {
}
