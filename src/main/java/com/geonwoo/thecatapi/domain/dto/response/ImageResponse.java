package com.geonwoo.thecatapi.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ImageResponse(
	String id,
	String url,
	String name,
	Integer width,
	Integer height,
	String temperament,
	String origin
) {
	@Builder
	public ImageResponse(String id, String url, String name, Integer width, Integer height, String temperament,
		String origin) {
		this.id = id;
		this.url = url;
		this.name = name;
		this.width = width;
		this.height = height;
		this.temperament = temperament;
		this.origin = origin;
	}

	public static ImageResponse create(String id, String url, String name) {
		return new ImageResponse(id, url, name, null, null, null, null);
	}
}
