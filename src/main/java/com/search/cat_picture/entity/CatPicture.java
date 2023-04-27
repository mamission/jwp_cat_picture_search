package com.search.cat_picture.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CatPicture {

	@Id
	String id;

	@Column(nullable = false)
	String url;

	Integer width;

	Integer height;

	@OneToOne(cascade = CascadeType.PERSIST)
	CatBreed breed;
}
