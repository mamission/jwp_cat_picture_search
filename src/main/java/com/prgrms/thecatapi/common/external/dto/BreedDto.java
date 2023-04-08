package com.prgrms.thecatapi.common.external.dto;

public record BreedDto(
	WeightDto weight,
	String id,
	String name,
	String temperament,
	String origin) {
}
