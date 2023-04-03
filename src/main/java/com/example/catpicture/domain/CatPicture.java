package com.example.catpicture.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CatPicture {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;

	@Column(nullable = false)
	private String url;

	@Column(nullable = false)
	private int width;

	@Column(nullable = false)
	private int height;

	@Embedded
	@Column(nullable = false)
	private BreedDetails breedDetails;

	protected CatPicture() {
	}

	public CatPicture(String id, String url, int width, int height, BreedDetails breedDetails) {
		this.id = id;
		this.url = url;
		this.width = width;
		this.height = height;
		this.breedDetails = breedDetails;
	}

	public String id() {
		return id;
	}

	public String url() {
		return url;
	}

	public int width() {
		return width;
	}

	public int height() {
		return height;
	}

	public BreedDetails breedDetails() {
		return breedDetails;
	}
}
