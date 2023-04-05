package com.pfk.thecats.infra.thecatapi.dto;

import java.util.List;

import com.pfk.thecats.domain.cat.entity.Cat;
import com.pfk.thecats.domain.cat.entity.CatImage;

public record CatImageSearchResponse(
	List<Breed> breeds,
	String id,
	String url,
	int width,
	int height
) {

	public static List<Cat> from(List<CatImageSearchResponse> responses) {
		return responses.stream()
			.filter(CatImageSearchResponse::hasBreed)
			.map(CatImageSearchResponse::from)
			.toList();
	}

	public static Cat from(CatImageSearchResponse response) {
		CatImage image = new CatImage(response.url, response.width, response.height);
		Breed breed = response.getBreed();
		return new Cat(breed.name, breed.temperament, breed.origin, image);
	}

	private Breed getBreed() {
		return this.breeds.get(0);
	}

	private boolean hasBreed() {
		return this.breeds.size() != 0;
	}

	public record Breed(
		String name,
		String temperament,
		String origin
	) {
	}
}
