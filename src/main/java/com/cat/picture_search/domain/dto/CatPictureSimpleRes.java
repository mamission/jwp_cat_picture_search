package com.cat.picture_search.domain.dto;

import com.cat.picture_search.domain.storage.data.CatPicture;
import com.cat.picture_search.domain.storage.repository.CatPictureSearchSimple;

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

	public static CatPictureSimpleRes of(CatPictureSearchSimple catPicture) {
		return new CatPictureSimpleRes(
			catPicture.id(),
			catPicture.url(),
			catPicture.name()
		);
	}
}
