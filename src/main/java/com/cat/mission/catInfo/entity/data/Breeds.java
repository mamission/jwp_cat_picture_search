package com.cat.mission.catInfo.entity.data;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;

@Embeddable
public class Breeds {

  @Column(nullable = false)
  private String id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String temperament;

  @Column(nullable = false)
  private String origin;

  @Embedded
  private Weight weight;

  protected Breeds() {
  }

  public Breeds(String id, String name, String temperament, String origin, Weight weight) {
    this.id = id;
    this.name = name;
    this.temperament = temperament;
    this.origin = origin;
    this.weight = weight;
  }

  public String getId() {
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

  public Weight getWeight() {
    return weight;
  }
}
