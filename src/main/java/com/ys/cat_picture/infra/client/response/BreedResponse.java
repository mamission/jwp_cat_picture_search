package com.ys.cat_picture.infra.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record BreedResponse(
	@JsonProperty("weight") Weight weight,
	@JsonProperty("id") String id,
	@JsonProperty("name") String name,
	@JsonProperty("cfa_url") String cfaUrl,
	@JsonProperty("vetstreet_url") String vetstreetUrl,
	@JsonProperty("vcahospitals_url") String vcahospitalsUrl,
	@JsonProperty("temperament") String temperament,
	@JsonProperty("origin") String origin,
	@JsonProperty("country_codes") String countryCodes,
	@JsonProperty("country_code") String countryCode,
	@JsonProperty("description") String description,
	@JsonProperty("life_span") String lifeSpan,
	@JsonProperty("indoor") int indoor,
	@JsonProperty("lap") int lap,
	@JsonProperty("alt_names") String altNames,
	@JsonProperty("adaptability") int adaptability,
	@JsonProperty("affection_level") int affectionLevel,
	@JsonProperty("child_friendly") int childFriendly,
	@JsonProperty("dog_friendly") int dogFriendly,
	@JsonProperty("energy_level") int energyLevel,
	@JsonProperty("grooming") int grooming,
	@JsonProperty("health_issues") int healthIssues,
	@JsonProperty("intelligence") int intelligence,
	@JsonProperty("shedding_level") int sheddingLevel,
	@JsonProperty("social_needs") int socialNeeds,
	@JsonProperty("stranger_friendly") int strangerFriendly,
	@JsonProperty("vocalisation") int vocalisation,
	@JsonProperty("experimental") int experimental,
	@JsonProperty("hairless") int hairless,
	@JsonProperty("natural_cat") int naturalCat,
	@JsonProperty("rare") int rare,
	@JsonProperty("rex") int rex,
	@JsonProperty("suppressed_tail") int suppressedTail,
	@JsonProperty("short_legs") int shortLegs,
	@JsonProperty("wikipedia_url") String wikipediaUrl,
	@JsonProperty("hypoallergenic") int hypoallergenic,
	@JsonProperty("reference_image_id") String referenceImageId
) {
	public record Weight(
		@JsonProperty("imperial") String imperial,
		@JsonProperty("metric") String metric
	) {}
}