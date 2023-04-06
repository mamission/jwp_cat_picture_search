package com.example.catpicture.domain;

public class CatPictureNotFoundException extends RuntimeException {

	public CatPictureNotFoundException(Long pictureId) {
		super("Cannot found cat picture. [picture id]: %d".formatted(pictureId));
	}
}
