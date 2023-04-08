package com.prgrms.thecatapi.common.dto;

import java.util.List;

import com.prgrms.thecatapi.cat.Photo;

public record DetailResponse(
	String id,
	String url,
	int width,
	int height,
	List<BreedResponse> breeds
) {
	public DetailResponse(String id, String url, int width, int height,
		List<BreedResponse> breeds) {
		this.id = id;
		this.url = url;
		this.width = width;
		this.height = height;
		this.breeds = breeds;
	}

	public DetailResponse(Photo photo, List<BreedResponse> breeds) {
		this(photo.getId(), photo.getUrl(), photo.getWidth(), photo.getHeight(), breeds);
	}
}
