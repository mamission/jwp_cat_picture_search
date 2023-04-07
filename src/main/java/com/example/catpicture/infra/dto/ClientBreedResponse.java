package com.example.catpicture.infra.dto;

import java.util.List;

import com.example.catpicture.domain.entity.BreedDetails;
import com.example.catpicture.infra.client.BreedEmptyException;

public record ClientBreedResponse(
	String id,
	String name,
	String temperament,
	String origin
) {

	public static BreedDetails toEntityFirst(List<ClientBreedResponse> responses) {
		if (responses.size() < 1) {
			throw new BreedEmptyException("Loaded Breed is empty!");
		}
		ClientBreedResponse first = responses.get(0);
		return new BreedDetails(first.name(), first.temperament(), first.origin());
	}
}
