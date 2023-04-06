package com.geonwoo.thcatapi.domain.catImage.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CatImage {

	@Id
	private String id;

	@Column
	private String name;

	@Column
	private String url;

	@Column
	private Integer width;

	@Column
	private Integer height;

	@Column
	private String temperament;

	@Column
	private String origin;

	@Builder
	protected CatImage(String id, String name, String url, Integer width, Integer height, String temperament,
		String origin) {
		this.id = id;
		this.name = name;
		this.url = url;
		this.width = width;
		this.height = height;
		this.temperament = temperament;
		this.origin = origin;
	}
}
