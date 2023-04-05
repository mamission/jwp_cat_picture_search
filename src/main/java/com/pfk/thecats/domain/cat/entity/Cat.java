package com.pfk.thecats.domain.cat.entity;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cat {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String breed;

	private String temperament;

	private String origin;

	@Embedded
	private CatImage image;

	protected Cat() {
	}

	public Cat(String breed, String temperament, String origin, CatImage image) {
		this.breed = breed;
		this.temperament = temperament;
		this.origin = origin;
		this.image = image;
	}

	public Long id() {
		return id;
	}

	public String breed() {
		return breed;
	}

	public String temperament() {
		return temperament;
	}

	public String origin() {
		return origin;
	}

	public CatImage image() {
		return image;
	}
}
