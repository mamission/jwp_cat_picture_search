package com.example.catpicture.domain.dto;

import java.util.List;

import com.example.catpicture.domain.entity.CatPicture;

public record GetRandomCatsResponse(
	List<RandomCatResponse> data
) {
	public void addCatResponse(CatPicture catPicture) {
		data.add(new RandomCatResponse(catPicture));
	}
}
