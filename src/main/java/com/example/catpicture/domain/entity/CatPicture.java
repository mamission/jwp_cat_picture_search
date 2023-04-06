package com.example.catpicture.domain.entity;

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
	private Long sequence;

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

	public Long sequence() {
		return sequence;
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

	@Override
	public String toString() {
		return "CatPicture{" +
			"id='" + id + '\'' +
			", url='" + url + '\'' +
			", width=" + width +
			", height=" + height +
			", breedDetails=" + breedDetails +
			"}\n";
	}

	public String getName() {
		return breedDetails.name();
	}
}
