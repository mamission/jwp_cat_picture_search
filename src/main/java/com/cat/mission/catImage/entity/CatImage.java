package com.cat.mission.catImage.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CatImage {

  @Id
  @Column(name = "cat_image_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String displayId;

  private String url;

  private int width;

  private int height;

  protected CatImage() {
  }

  public CatImage(String displayId, String url, int width, int height) {
    this.displayId = displayId;
    this.url = url;
    this.width = width;
    this.height = height;
  }

  public Long getId() {
    return id;
  }

  public String getDisplayId() {
    return displayId;
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
