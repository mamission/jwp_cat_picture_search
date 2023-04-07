package com.example.catpicturesearch.application.domain;

import jakarta.persistence.Entity;
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
@Getter
@Table(name = "cat_breed")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CatBreed {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "breed_id", nullable = false)
	private Breed breed;

	@ManyToOne
	@JoinColumn(name = "cat_image_id", nullable = false)
	private CatImage catImage;

	@Builder
	public CatBreed(Long id, Breed breed, CatImage catImage) {
		this.id = id;
		this.breed = breed;
		this.catImage = catImage;
	}

}
