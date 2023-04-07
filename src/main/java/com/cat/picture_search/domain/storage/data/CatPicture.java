package com.cat.picture_search.domain.storage.data;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "cat-picture")
public class CatPicture {

	@Id
	@Column(nullable = false)
	private String id;

	@Column(nullable = false)
	private String url;

	private Long width;

	private Long height;

	@Embedded
	private Breeds breeds;

	protected CatPicture() {
	}

	public CatPicture(String id, String url, Long width, Long height, Breeds breeds) {
		checkNull(id, url);
		this.id = id;
		this.url = url;
		this.width = width;
		this.height = height;
		this.breeds = breeds;
	}

	private void checkNull(String id, String url) {
		if (Objects.isNull(id)) {
			throw new IllegalArgumentException("id는 빈 값일 수 없습니다.");
		}

		if (Objects.isNull(url)) {
			throw new IllegalArgumentException("url은 빈 값일 수 없습니다.");
		}
	}

	public String getId() {
		return id;
	}

	public String getUrl() {
		return url;
	}

	public Long getWidth() {
		return width;
	}

	public Long getHeight() {
		return height;
	}

	public Breeds getBreeds() {
		return breeds;
	}
}
