package com.ys.cat_picture.cat_breed.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cat_breeds")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CatBreed {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private String externalId;

	@Embedded
	private Weight weight;

	@Column(name = "name", unique = true)
	private String name;

	@Column(name = "cfa_url")
	private String cfaUrl;

	@Column(name = "vetstreet_url")
	private String vetstreetUrl;

	@Column(name = "vcahospitals_url")
	private String vcahospitalsUrl;

	@Column(name = "temperament")
	private String temperament;

	@Column(name = "origin")
	private String origin;

	@Column(name = "country_codes")
	private String countryCodes;

	@Column(name = "country_code")
	private String countryCode;

	@Column(name = "description", length = 10000)
	private String description;

	@Column(name = "life_span")
	private String lifeSpan;

	@Column(name = "indoor")
	private int indoor;

	@Column(name = "lap")
	private int lap;

	@Column(name = "alt_names")
	private String altNames;

	@Column(name = "adaptability")
	private int adaptability;

	@Column(name = "affection_level")
	private int affectionLevel;

	@Column(name = "child_friendly")
	private int childFriendly;

	@Column(name = "dog_friendly")
	private int dogFriendly;

	@Column(name = "cat_friendly")
	private int catFriendly;

	@Column(name = "energy_level")
	private int energyLevel;

	@Column(name = "grooming")
	private int grooming;

	@Column(name = "health_issues")
	private int healthIssues;

	@Column(name = "intelligence")
	private int intelligence;

	@Column(name = "shedding_level")
	private int sheddingLevel;

	@Column(name = "social_needs")
	private int socialNeeds;

	@Column(name = "stranger_friendly")
	private int strangerFriendly;

	@Column(name = "vocalisation")
	private int vocalisation;

	@Column(name = "bidability")
	private int bidability;

	@Column(name = "experimental")
	private int experimental;

	@Column(name = "hairless")
	private int hairless;

	@Column(name = "natural_cat")
	private int natural;

	@Column(name = "rare")
	private int rare;

	@Column(name = "rex")
	private int rex;

	@Column(name = "suppressed_tail")
	private int suppressedTail;

	@Column(name = "short_legs")
	private int shortLegs;

	@Column(name = "wikipedia_url")
	private String wikipediaUrl;

	@Column(name = "hypoallergenic")
	private int hypoallergenic;

	@Column(name = "reference_image_id")
	private String referenceImageId;

	@Builder
	public CatBreed(String externalId, Weight weight, String name, String cfaUrl, String vetstreetUrl,
		String vcahospitalsUrl,
		String temperament, String origin, String countryCodes, String countryCode, String description, String lifeSpan,
		int indoor, int lap, String altNames, int adaptability, int affectionLevel, int childFriendly, int dogFriendly,
		int catFriendly,
		int energyLevel, int grooming, int healthIssues, int intelligence, int sheddingLevel, int socialNeeds,
		int strangerFriendly, int vocalisation, int bidability, int experimental, int hairless, int natural, int rare,
		int rex,
		int suppressedTail, int shortLegs, String wikipediaUrl, int hypoallergenic, String referenceImageId) {
		this.externalId = externalId;
		this.experimental = experimental;
		this.weight = weight;
		this.name = name;
		this.cfaUrl = cfaUrl;
		this.vetstreetUrl = vetstreetUrl;
		this.vcahospitalsUrl = vcahospitalsUrl;
		this.temperament = temperament;
		this.origin = origin;
		this.countryCodes = countryCodes;
		this.countryCode = countryCode;
		this.description = description;
		this.lifeSpan = lifeSpan;
		this.indoor = indoor;
		this.lap = lap;
		this.altNames = altNames;
		this.adaptability = adaptability;
		this.affectionLevel = affectionLevel;
		this.childFriendly = childFriendly;
		this.dogFriendly = dogFriendly;
		this.energyLevel = energyLevel;
		this.grooming = grooming;
		this.healthIssues = healthIssues;
		this.intelligence = intelligence;
		this.sheddingLevel = sheddingLevel;
		this.socialNeeds = socialNeeds;
		this.strangerFriendly = strangerFriendly;
		this.vocalisation = vocalisation;
		this.bidability = bidability;
		this.catFriendly = catFriendly;
		this.hairless = hairless;
		this.natural = natural;
		this.rare = rare;
		this.rex = rex;
		this.suppressedTail = suppressedTail;
		this.shortLegs = shortLegs;
		this.wikipediaUrl = wikipediaUrl;
		this.hypoallergenic = hypoallergenic;
		this.referenceImageId = referenceImageId;
	}
}
