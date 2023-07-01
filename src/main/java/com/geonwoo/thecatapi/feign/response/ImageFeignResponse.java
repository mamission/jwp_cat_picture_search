package com.geonwoo.thecatapi.feign.response;

import java.util.List;

public record ImageFeignResponse(
	String id,
	String url,
	Integer width,
	Integer height,
	List<BreedFeignResponse> breeds
) {
}
