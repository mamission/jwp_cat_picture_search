package com.example.catpicturesearch.application.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "breed")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Breed {

	@Id
	@Column(name = "id", unique = true, nullable = false)
	private String id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "temperament", nullable = false)
	private String temperament;

	@Column(name = "origin", nullable = false)
	private String origin;

	@Builder
	public Breed(String id, String name, String temperament, String origin) {
		this.id = id;
		this.name = name;
		this.temperament = temperament;
		this.origin = origin;
	}

}
