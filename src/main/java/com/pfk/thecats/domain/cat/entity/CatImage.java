package com.pfk.thecats.domain.cat.entity;

import javax.persistence.Embeddable;

@Embeddable
public class CatImage {

	private String url;
	private int width;
	private int height;

	protected CatImage() {
	}

	public CatImage(String url, int width, int height) {
		this.url = url;
		this.width = width;
		this.height = height;
	}

	public String url() {
		return url;
	}

	public int width() {
		return width;
	}

	public int height() {
		return height;
	}
}
