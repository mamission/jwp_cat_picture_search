package com.cat.mission.catBreeds.entity.data;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Weight {

  @Column
  private String imperial;

  @Column
  private String metric;

  public Weight(String imperial, String metric) {
    this.imperial = imperial;
    this.metric = metric;
  }

  protected Weight() {
  }

  public String getImperial() {
    return imperial;
  }

  public String getMetric() {
    return metric;
  }
}
