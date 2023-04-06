package com.geonwoo.thecatapi.feign.response;

public record BreedFeignResponse(
	String name,
	String temperament,
	String origin
) {
}
