package com.cat.picture_search.domain.dto.res;

import com.cat.picture_search.domain.storage.data.CatPicture;

public record CatPictureDetailRes(
	String name,
	String id,
	String url,
	long width,
	long height,
	String temperament,
	String origin
) {

	public static CatPictureDetailRes of(CatPicture catPicture) {
		return new CatPictureDetailRes(
			catPicture.getBreeds().getName(),
			catPicture.getId(),
			catPicture.getUrl(),
			catPicture.getWidth(),
			catPicture.getHeight(),
			catPicture.getBreeds().getTemperament(),
			catPicture.getBreeds().getOrigin()
		);
	}
}
