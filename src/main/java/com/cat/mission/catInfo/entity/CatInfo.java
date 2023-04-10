package com.cat.mission.catInfo.entity;

import com.cat.mission.catInfo.entity.data.Breeds;
import com.cat.mission.catInfo.entity.data.Image;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CatInfo {

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

  protected CatInfo() {
  }

  public CatInfo(String displayId, Image image, Breeds breeds) {
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
