package com.ys.cat_picture.infra.client.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CatOneResponse(
	@JsonProperty("id") String id,
	@JsonProperty("width") int width,
	@JsonProperty("height") int height,
	@JsonProperty("url") String url,
	@JsonProperty("breeds") List<BreedResponse> breeds) {

	public record BreedResponse(
		@JsonProperty("id") String id,
		@JsonProperty("name") String name,
		@JsonProperty("temperament") String temperament,
		@JsonProperty("origin") String origin) {
	}

}