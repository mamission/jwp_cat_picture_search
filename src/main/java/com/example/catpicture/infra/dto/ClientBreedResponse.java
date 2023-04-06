package com.example.catpicture.infra.dto;

import com.example.catpicture.domain.entity.BreedDetails;

public record ClientBreedResponse(
	String id,
	String name,
	String temperament,
	String origin
) {
	
	public BreedDetails toEntity() {
		return new BreedDetails(
			this.name,
			this.temperament,
			this.origin
		);
	}
}
