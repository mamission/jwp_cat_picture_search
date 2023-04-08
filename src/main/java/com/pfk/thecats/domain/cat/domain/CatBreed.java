package com.pfk.thecats.domain.cat.domain;

import static javax.persistence.GenerationType.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class CatBreed {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	@Column(updatable = false, nullable = false)
	private String name;

	@Column(nullable = false)
	private String temperament;

	@Column(nullable = false)
	private String origin;

	protected CatBreed() {
	}

	public CatBreed(String name, String temperament, String origin) {
		this.name = name;
		this.temperament = temperament;
		this.origin = origin;
	}

	public Long id() {
		return id;
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
}
