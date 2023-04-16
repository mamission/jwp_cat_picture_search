package com.example.catpicture.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class BreedDetails {

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String temperament;

	@Column(nullable = false)
	private String origin;

	protected BreedDetails() {
	}

	public BreedDetails(String name, String temperament, String origin) {
		this.name = name;
		this.temperament = temperament;
		this.origin = origin;
	}

	public String name() {
		return name;
	}

	public String temperament() {
		return temperament;
	}

	public String origin() {
		return origin;
	}

	@Override
	public String toString() {
		return "BreedDetails{" +
			"name='" + name + '\'' +
			", temperament='" + temperament + '\'' +
			", origin='" + origin + '\'' +
			'}';
	}
}
