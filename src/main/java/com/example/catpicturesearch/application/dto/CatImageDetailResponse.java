package com.example.catpicturesearch.application.dto;

public record CatImageDetailResponse(
		String id,
		String url,
		String name,
		int width,
		int height,
		String temperament,
		String origin
) {
}
