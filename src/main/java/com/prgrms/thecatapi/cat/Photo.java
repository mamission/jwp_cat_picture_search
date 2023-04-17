package com.prgrms.thecatapi.cat;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Photo {

	@Id
	private String id;

	@Column(nullable = false)
	private String url;

	@Column(nullable = false)
	private int width;

	@Column(nullable = false)
	private int height;

	@OneToMany(mappedBy = "photo")
	private final List<PhotoBreed> photoBreeds = new ArrayList<>();

	public Photo(String id, String url, int width, int height) {
		this.id = id;
		this.url = url;
		this.width = width;
		this.height = height;
	}

}
