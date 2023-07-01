package com.geonwoo.thecatapi.domain.catImage.exception;

public class CatImageNotFoundException extends RuntimeException {

	private static final String MESSAGE = "고양이 이미지 데이터가 없어요!";

	public CatImageNotFoundException() {
		super(MESSAGE);
	}
}
