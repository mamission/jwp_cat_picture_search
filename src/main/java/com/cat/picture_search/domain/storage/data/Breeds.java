package com.cat.picture_search.domain.storage.data;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Breeds {

	@Column
	private String name;

	@Column
	private String temperament;

	@Column
	private String origin;

	protected Breeds() {
	}

	public Breeds(String name, String temperament, String origin) {
		this.name = name;
		this.temperament = temperament;
		this.origin = origin;
	}

	public String getName() {
		return name;
	}

	public String getTemperament() {
		return temperament;
	}

	public String getOrigin() {
		return origin;
	}
}
