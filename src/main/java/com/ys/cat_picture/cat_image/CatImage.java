package com.ys.cat_picture.cat_image;

import com.ys.cat_picture.cat_breed.domain.CatBreed;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cat_images")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CatImage {
	@Id
	private String id;
	private String url;
	private int width;
	private int height;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cat_breed_id")
	private CatBreed breed;

}
