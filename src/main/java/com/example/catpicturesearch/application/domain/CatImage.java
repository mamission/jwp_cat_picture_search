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
@Table(name = "cat_image")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CatImage {

	@Id
	@Column(name = "id", unique = true)
	private String id;

	@Column(name = "url", nullable = false)
	private String url;

	@Column(name = "width")
	private int width;

	@Column(name = "height")
	private int height;

	@Builder
	public CatImage(String id, String url, int width, int height) {
		this.id = id;
		this.url = url;
		this.width = width;
		this.height = height;
	}

}
