package com.example.catpicture.domain.dto;

import java.util.List;

import com.example.catpicture.domain.entity.CatPicture;

public record GetRandomCatsResponse(
	List<RandomCatResponse> randomCatResponses
) {
	public void addCatResponse(CatPicture catPicture) {
		randomCatResponses.add(new RandomCatResponse(catPicture));
	}
}
