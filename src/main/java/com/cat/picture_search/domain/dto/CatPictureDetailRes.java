package com.cat.picture_search.domain.dto;

import java.util.Objects;
import java.util.stream.Collectors;

import com.cat.picture_search.domain.dto.api.BreedsDetail;
import com.cat.picture_search.domain.dto.api.CatPictureDetail;

public record CatPictureDetailRes(
	String name,
	String id,
	String url,
	long width,
	long height,
	String temperament,
	String origin
) {
	private static final String DELIMITER = ",";

	public static CatPictureDetailRes of(CatPictureDetail catPictureDetail) {
		if (Objects.isNull(catPictureDetail.breeds())) {
			return new CatPictureDetailRes(
				null,
				catPictureDetail.id(),
				catPictureDetail.url(),
				catPictureDetail.width(),
				catPictureDetail.height(),
				null,
				null
			);
		}

		return new CatPictureDetailRes(
			catPictureDetail.breeds()
				.stream()
				.map(BreedsDetail::name)
				.collect(Collectors.joining(DELIMITER)),
			catPictureDetail.id(),
			catPictureDetail.url(),
			catPictureDetail.width(),
			catPictureDetail.height(),
			catPictureDetail.breeds()
				.stream()
				.map(BreedsDetail::temperament)
				.collect(Collectors.joining(DELIMITER)),
			catPictureDetail.breeds()
				.stream()
				.map(BreedsDetail::origin)
				.collect(Collectors.joining(DELIMITER)));
	}
}
