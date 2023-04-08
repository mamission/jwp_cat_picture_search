package com.pfk.thecats.domain.type;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Image {

	@Column(name = "image_url", nullable = false)
	private String url;

	@Column(name = "image_width", nullable = false)
	private int width;

	@Column(name = "image_height", nullable = false)
	private int height;

	protected Image() {
	}

	public Image(String url, int width, int height) {
		this.url = url;
		this.width = width;
		this.height = height;
	}

	public String url() {
		return url;
	}

	public int height() {
		return height;
	}

	public int width() {
		return width;
	}
}
