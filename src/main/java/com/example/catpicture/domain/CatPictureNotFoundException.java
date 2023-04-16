package com.example.catpicture.domain;

public class CatPictureNotFoundException extends RuntimeException {

	public CatPictureNotFoundException(String message) {
		super(message);
	}
}
