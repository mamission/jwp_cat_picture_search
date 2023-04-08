package com.pfk.thecats.domain.cat.domain;

import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.*;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.pfk.thecats.domain.type.Image;

@Entity
public class Cat {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String sourceId;

	@ManyToOne(fetch = LAZY)
	private CatBreed breed;

	@Embedded
	private Image image;

	protected Cat() {
	}

	public Cat(String sourceId, CatBreed breed, Image image) {
		this.sourceId = sourceId;
		this.breed = breed;
		this.image = image;
	}

	public String getBreedName() {
		return this.breed.name();
	}

	public String getImageUrl() {
		return this.image().url();
	}

	public Long id() {
		return id;
	}

	public String sourceId() {
		return sourceId;
	}

	public CatBreed breed() {
		return breed;
	}

	public Image image() {
		return image;
	}
}
