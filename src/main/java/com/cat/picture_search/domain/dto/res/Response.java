package com.cat.picture_search.domain.dto.res;

public record Response<E>(
	E data
) {

	public static <T> Response<T> of(T data) {
		return new Response<>(data);
	}
}
