package com.cat.mission.catBreeds.entity;

import com.cat.mission.catBreeds.entity.data.Weight;
import com.cat.mission.catImage.entity.CatImage;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Breeds {

  @Id
  @Column(name = "breeds_id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String displayId;

  private String name;

  private String temperament;

  private String origin;

  @Embedded
  private Weight weight;

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "cat_image_id")
  private CatImage catImage;

  protected Breeds() {
  }

  public Breeds(String displayId, String name, String temperament, String origin, Weight weight,
      CatImage catImage) {
    this.displayId = displayId;
    this.name = name;
    this.temperament = temperament;
    this.origin = origin;
    this.weight = weight;
    this.catImage = catImage;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getTemperament() {
    return temperament;
  }

  public String getOrigin() {
    return origin;
  }

  public String getDisplayId() {
    return displayId;
  }

  public Weight getWeight() {
    return weight;
  }

  public CatImage getCatImage() {
    return catImage;
  }
}
