package com.example.catpicturesearch.application.clients.dto;

public record CatImageResponse(
		String id,
		int width,
		int height,
		String url
) {
}
