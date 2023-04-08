package com.pfk.thecats.infra.thecatapi.dto;

import java.util.List;

import com.pfk.thecats.domain.cat.domain.Cat;
import com.pfk.thecats.domain.cat.domain.CatBreed;

public record CatImageSearchResponse(
	List<Breed> breeds,
	String id,
	String url,
	int width,
	int height
) {

	public static List<Cat> mapToEntity(List<CatImageSearchResponse> responses) {
		return responses.stream()
			.filter(CatImageSearchResponse::hasBreed)
			.map(CatImageSearchResponse::mapToEntity)
			.toList();
	}

	public static Cat mapToEntity(CatImageSearchResponse response) {
		return null;
	}

	public Breed getBreed() {
		return this.breeds.get(0);
	}

	private boolean hasBreed() {
		return !this.breeds.isEmpty();
	}

	public record Breed(
		String name,
		String temperament,
		String origin
	) {

		public CatBreed toEntity() {
			return new CatBreed(name, temperament, origin);
		}
	}
}
