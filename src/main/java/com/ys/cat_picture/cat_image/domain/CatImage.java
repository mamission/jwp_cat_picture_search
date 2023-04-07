package com.ys.cat_picture.cat_image.domain;

import java.util.Objects;

import com.ys.cat_picture.cat_breed.domain.CatBreed;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cat_images")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CatImage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private String externalId;
	private String url;

	private Integer width;
	private Integer height;

	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
	@JoinColumn(name = "cat_breed_id")
	private CatBreed breed;

	@Builder
	public CatImage(String externalId, String url, int width, int height, CatBreed breed) {
		this.externalId = externalId;
		this.url = url;
		this.width = width;
		this.height = height;
		this.breed = breed;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		CatImage catImage = (CatImage)o;
		return Objects.equals(id, catImage.id) && Objects.equals(externalId, catImage.externalId)
			&& Objects.equals(url, catImage.url) && Objects.equals(width, catImage.width)
			&& Objects.equals(height, catImage.height) && Objects.equals(breed, catImage.breed);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, externalId, url, width, height, breed);
	}

}
