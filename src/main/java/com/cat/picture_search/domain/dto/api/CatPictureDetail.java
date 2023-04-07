package com.cat.picture_search.domain.dto.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.cat.picture_search.domain.storage.data.Breeds;
import com.cat.picture_search.domain.storage.data.CatPicture;

public record CatPictureDetail(
	String id,
	String url,
	long width,
	long height,
	List<BreedsDetail> breeds
) {
	private static final String DELIMITER = ",";

	public CatPicture toEntity() {
		if (!Objects.isNull(breeds)) {
			List<String> name = new ArrayList<>();
			List<String> temperament = new ArrayList<>();
			List<String> origin = new ArrayList<>();

			breeds.forEach(breedsDetail -> {
				name.add(breedsDetail.name());
				temperament.add(breedsDetail.temperament());
				origin.add(breedsDetail.origin());
			});

			return new CatPicture(
				id,
				url,
				width,
				height,
				new Breeds(
					String.join(DELIMITER, name),
					String.join(DELIMITER, temperament),
					String.join(DELIMITER, origin)
				)
			);
		}

		return new CatPicture(
			id,
			url,
			width,
			height,
			null
		);
	}
}
