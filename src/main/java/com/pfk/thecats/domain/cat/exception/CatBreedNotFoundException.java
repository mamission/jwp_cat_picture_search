package com.pfk.thecats.domain.cat.exception;

public class CatBreedNotFoundException extends RuntimeException {

	public CatBreedNotFoundException(String message) {
		super(message);
	}
}
