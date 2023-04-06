package com.ys.cat_picture.infra.client.response;

public record CatResponse(
	String id,
	String url,
	int width,
	int height
) {
}
