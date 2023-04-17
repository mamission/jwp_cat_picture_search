package com.prgrms.thecatapi.cat;

import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = PROTECTED)
public class PhotoBreed {
	@Id
	@GeneratedValue(strategy = AUTO)
	private Long id;

	@ManyToOne(fetch = LAZY, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "photo_id")
	private Photo photo;

	@ManyToOne(fetch = LAZY, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "breed_id")
	private Breed breed;

	public PhotoBreed(Photo photo, Breed breed) {
		this.photo = photo;
		this.breed = breed;
	}

	public void addPhoto(Photo photo) {
		this.photo = photo;
		photo.getPhotoBreeds().add(this);
	}
}
