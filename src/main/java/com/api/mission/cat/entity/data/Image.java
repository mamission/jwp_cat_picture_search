package com.api.mission.cat.entity.data;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Image {

  @Column(nullable = false)
  private String url;

  @Column(nullable = false)
  private int width;

  @Column(nullable = false)
  private int height;

  protected Image() {
  }

  public Image(String url, int width, int height) {
    this.url = url;
    this.width = width;
    this.height = height;
  }

  public String getUrl() {
    return url;
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }
}
