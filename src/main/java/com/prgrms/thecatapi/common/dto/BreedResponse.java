package com.prgrms.thecatapi.common.dto;

public record BreedResponse(
	String id,
	String name,
	String temperament,
	String origin
) {
}
