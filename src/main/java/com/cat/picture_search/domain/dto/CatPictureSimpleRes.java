package com.cat.picture_search.domain.dto;

import com.cat.picture_search.domain.storage.data.CatPicture;

public record CatPictureSimpleRes(
	String id,
	String url,
	String name
) {

	public static CatPictureSimpleRes of(CatPicture catPicture) {
		return new CatPictureSimpleRes(
			catPicture.getId(),
			catPicture.getUrl(),
			catPicture.getBreeds().getName()
		);
	}
}
