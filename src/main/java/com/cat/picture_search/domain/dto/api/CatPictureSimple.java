package com.cat.picture_search.domain.dto.api;

import java.util.List;

public record CatPictureSimple(
	String id,
	String url,
	List<BreedsDetail> breeds
) {
}
