package com.api.mission.cat.entity;

import com.api.mission.cat.entity.data.Breeds;
import com.api.mission.cat.entity.data.Image;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Cat {

  @Id
  @Column(name = "cat_info_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String displayId;

  @Embedded
  private Image image;

  @Embedded
  private Breeds breeds;

  protected Cat() {
  }

  public Cat(String displayId, Image image, Breeds breeds) {
    this.displayId = displayId;
    this.image = image;
    this.breeds = breeds;
  }

  public Long getId() {
    return id;
  }

  public String getDisplayId() {
    return displayId;
  }

  public Image getImage() {
    return image;
  }

  public Breeds getBreeds() {
    return breeds;
  }
}
