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

	@Column(name = "name", unique = true)
	private String name;

	@Column(name = "temperament")
	private String temperament;

	@Column(name = "origin")
	private String origin;

	@Builder
	public CatBreed(String externalId, String name, String temperament, String origin) {
		this.externalId = externalId;
		this.name = name;
		this.temperament = temperament;
		this.origin = origin;
	}

}
