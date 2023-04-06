package com.ys.cat_picture.cat_breed.convert;

import com.ys.cat_picture.cat_breed.domain.CatBreed;
import com.ys.cat_picture.cat_breed.domain.Weight;
import com.ys.cat_picture.infra.client.response.CatOneResponse;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CatBreedConverter {

	public static CatBreed convert(CatOneResponse.BreedResponse response) {
		if (response == null) {
			return null;
		}

		return CatBreed.builder()
			.weight(new Weight(response.weight().imperial(), response.weight().metric()))
			.externalId(response.id())
			.name(response.name())
			.cfaUrl(response.cfaUrl())
			.temperament(response.temperament())
			.origin(response.origin())
			.countryCodes(response.countryCodes())
			.countryCode(response.countryCode())
			.description(response.description())
			.lifeSpan(response.lifeSpan())
			.adaptability(response.adaptability())
			.affectionLevel(response.affectionLevel())
			.childFriendly(response.childFriendly())
			.catFriendly(response.catFriendly())
			.dogFriendly(response.dogFriendly())
			.energyLevel(response.energyLevel())
			.grooming(response.grooming())
			.healthIssues(response.healthIssues())
			.intelligence(response.intelligence())
			.sheddingLevel(response.sheddingLevel())
			.socialNeeds(response.socialNeeds())
			.strangerFriendly(response.strangerFriendly())
			.vocalisation(response.vocalisation())
			.hypoallergenic(response.hypoallergenic())
			.referenceImageId(response.referenceImageId())
			.build();
	}
}
